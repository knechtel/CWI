package com.cwi.demo.service;

import com.cwi.demo.DAO.PautaDAO;
import com.cwi.demo.bean.Pauta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    @Autowired
    private PautaDAO pautaDAO;

    public void save(Pauta pauta) {
        pautaDAO.save(pauta);
    }

    public Pauta findById(Integer id) {
        return pautaDAO.findById(id).orElse(null);
    }
}
