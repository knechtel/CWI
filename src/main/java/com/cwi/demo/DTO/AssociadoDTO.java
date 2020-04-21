package com.cwi.demo.DTO;

import com.cwi.demo.bean.Associado;

public class AssociadoDTO {

    private String nome;
    private String cpf;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Associado build(){
        Associado a = new Associado();
        a.setCpf(cpf);
        a.setNome(nome);
        return a;
    }
}
