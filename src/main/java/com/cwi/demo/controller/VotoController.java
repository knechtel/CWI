package com.cwi.demo.controller;

;
import com.cwi.demo.DTO.VotoDTO;
import com.cwi.demo.DTO.VotoFormDTO;
import com.cwi.demo.bean.Associado;
import com.cwi.demo.bean.Pauta;
import com.cwi.demo.bean.Voto;
import com.cwi.demo.service.AssociadoService;
import com.cwi.demo.service.PautaService;
import com.cwi.demo.service.VotoService;
import com.cwi.demo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voto")
public class VotoController {
    @Autowired
    private VotoService votoService;
    @Autowired
    private AssociadoService associadoService;
    @Autowired
    private PautaService pautaService;

    @RequestMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public VotoFormDTO save(@RequestBody VotoDTO votoDTO) {
        if (votoService.associadoVotou(votoDTO)) {
            VotoFormDTO votoForm = new VotoFormDTO();
            votoForm.setStatus("UNABLE_TO_VOTE!");
            return votoForm;
        } else {
            Associado associado = associadoService.findById(votoDTO.getIdAssociado());
            Pauta pauta = pautaService.findById(votoDTO.getIdPauta());

            List<Pauta> listPauta = Util.getListPauta();


            for (Pauta pmemory : listPauta) {
                if (pmemory.getId() == pauta.getId() && pmemory.isPossibleToVote() == true) {
                    Voto voto = new Voto();
                    voto.setVotoEnum(votoDTO.getVotoEnum());
                    voto.setAssociado(associado);
                    voto.setPauta(pauta);
                    votoService.save(voto);
                    VotoFormDTO votoForm = new VotoFormDTO();
                    votoForm.setStatus("REGISTERED_VOTE!");
                    return votoForm;
                }
            }


        }

        VotoFormDTO votoForm = new VotoFormDTO();
        votoForm.setStatus("UNABLE_TO_VOTE!");
        return votoForm;


    }
}
