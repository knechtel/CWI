package com.cwi.demo.controller;

import com.cwi.demo.DTO.PautaDTO;
import com.cwi.demo.bean.Pauta;
import com.cwi.demo.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pauta")
public class PautaController {
    @Autowired
    private PautaService pautaService;

    @RequestMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public HttpEntity<?> save(@RequestBody PautaDTO pautaDTO) {
        pautaService.save(pautaDTO.build(pautaDTO));
        return ResponseEntity.ok().build();

    }
}
