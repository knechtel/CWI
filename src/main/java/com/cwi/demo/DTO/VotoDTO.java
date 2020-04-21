package com.cwi.demo.DTO;


import com.cwi.demo.bean.VotoEnum;

public class VotoDTO {

    private Integer id;
    private VotoEnum votoEnum;
    private Integer idPauta;
    private Integer idAssociado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VotoEnum getVotoEnum() {
        return votoEnum;
    }

    public void setVotoEnum(VotoEnum votoEnum) {
        this.votoEnum = votoEnum;
    }

    public Integer getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Integer idPauta) {
        this.idPauta = idPauta;
    }

    public Integer getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Integer idAssociado) {
        this.idAssociado = idAssociado;
    }


}
