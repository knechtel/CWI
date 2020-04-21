package com.cwi.demo.DTO;

import com.cwi.demo.bean.Pauta;
import org.springframework.beans.factory.annotation.Value;

public class PautaDTO {

    private String texto ;
    private Integer second = 60;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Pauta build(PautaDTO pautaDTO){
        Pauta p = new Pauta();
        p.setTexto(pautaDTO.getTexto());
        p.setSecond(second);
        return p;
    }
}
