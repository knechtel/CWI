package com.cwi.demo.controller;

import com.cwi.demo.Application;
import com.cwi.demo.DTO.PautaDTO;


import com.cwi.demo.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/restart")
    void restart() {
        Thread restartThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                Application.restart();
            } catch (InterruptedException ignored) {
            }
        });
        restartThread.setDaemon(false);
        restartThread.start();
    }
}
