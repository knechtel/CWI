package com.cwi.demo.schedule;

import com.cwi.demo.DAO.PautaDAO;
import com.cwi.demo.bean.Pauta;
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
    PautaDAO pautaDAO;

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
        for (Pauta p : pautaDAO.findAll()) {
            Util.getListPauta().add(p);
            taskRegistrar.addTriggerTask(() -> scheduleDynamically(), t -> {
                Calendar nextExecutionTime = new GregorianCalendar();
                Date lastActualExecutionTime = t.lastActualExecutionTime();
                if(p.isPossibleToVote()){
                    p.setPossibleToVote(false);
                    System.out.println("Fecho a sessao!  "+p.isPossibleToVote());
                    return null;
                }
                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                nextExecutionTime.add(Calendar.SECOND, p.getSecond());

                if (p.isPossibleToVote()) {
                    p.setPossibleToVote(false);
                } else {
                    p.setPossibleToVote(!p.isPossibleToVote());
                }

                return nextExecutionTime.getTime();
            });
        }

    }

    public void scheduleDynamically() {

    }


}
