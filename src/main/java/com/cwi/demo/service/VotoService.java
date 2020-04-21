package com.cwi.demo.service;

import com.cwi.demo.DAO.VotoDAO;
import com.cwi.demo.DTO.VotoDTO;
import com.cwi.demo.bean.Voto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoService {

    @Autowired
    private VotoDAO votoDAO;

    public boolean associadoVotou(VotoDTO votoDTO) {
        List<Voto> listVoto = votoDAO.findByIdAssociadoAndPauta(votoDTO.getIdAssociado(), votoDTO.getIdPauta());
        if (listVoto != null && listVoto.size() > 0) {
            return true;
        }
        return false;
    }

    public Voto save(Voto voto){
        return votoDAO.save(voto);
    }

}
