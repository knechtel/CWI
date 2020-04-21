package com.cwi.demo.schedule;

import com.cwi.demo.DAO.PautaDAO;
import com.cwi.demo.DAO.VotoDAO;
import com.cwi.demo.bean.Pauta;
import com.cwi.demo.bean.Voto;
import com.cwi.demo.bean.VotoEnum;
import com.cwi.demo.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class DynamicScheduler implements SchedulingConfigurer {

    private static Logger LOGGER = LoggerFactory.getLogger(DynamicScheduler.class);

    @Autowired
    private PautaDAO pautaDAO;

    @Autowired
    private VotoDAO votoDAO;

    @PostConstruct
    public void initDatabase() {
    }

    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(1);
        scheduler.initialize();
        return scheduler;
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        for (Pauta pauta : pautaDAO.findAll()) {
            Util.getListPauta().add(pauta);
            taskRegistrar.addTriggerTask(() -> scheduleDynamically(), t -> {
                Calendar nextExecutionTime = new GregorianCalendar();
                Date lastActualExecutionTime = t.lastActualExecutionTime();
                if (pauta.isPossibleToVote()) {
                    pauta.setPossibleToVote(false);
                    List<Voto> listVoto = votoDAO.findClosePauta(pauta.getId());
                    Integer cntSim = 0, cntNao = 0;

                    for (Voto v : listVoto) {
                        if (v.getVotoEnum() == VotoEnum.NAO) {
                            cntNao++;
                        } else if (v.getVotoEnum() == VotoEnum.SIM) {
                            cntSim++;
                        }
                    }
                    System.out.println(pauta.getTexto());
                    System.out.println("id = " + pauta.getId());
                    System.out.println("votos SIM = " + cntSim);
                    System.out.println("votos NAO = " + cntNao);
                    return null;
                }
                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                nextExecutionTime.add(Calendar.SECOND, pauta.getSecond());

                if (pauta.isPossibleToVote()) {
                    pauta.setPossibleToVote(false);
                } else {
                    pauta.setPossibleToVote(!pauta.isPossibleToVote());
                }

                return nextExecutionTime.getTime();
            });
        }

    }

    public void scheduleDynamically() {

    }


}
