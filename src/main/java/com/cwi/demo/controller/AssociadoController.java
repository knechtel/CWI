package com.cwi.demo.controller;


import com.cwi.demo.DTO.AssociadoDTO;
import com.cwi.demo.DTO.UserDTO;
import com.cwi.demo.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/associado")
public class AssociadoController {
    @Autowired
    private AssociadoService associadoService;

    @RequestMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public UserDTO save(@RequestBody AssociadoDTO associadoDTO) {
        if (associadoService.validaCPF(associadoDTO)) {
            associadoService.save(associadoDTO.build());
            return new UserDTO("ABLE_TO_VOTE");
        } else {
            return new UserDTO("UNABLE_TO_VOTE");
        }

    }
}
