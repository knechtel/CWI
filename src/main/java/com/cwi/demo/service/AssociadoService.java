package com.cwi.demo.service;

import com.cwi.demo.DAO.AssociadoDAO;
import com.cwi.demo.bean.Associado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoDAO associadoDAO;


    public Associado save(Associado associado){
        return associadoDAO.save(associado);
    }

    public Associado findById(Integer id){
        return associadoDAO.findById(id).orElse(null);
    }
}
