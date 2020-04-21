package com.cwi.demo.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "pauta")
public class Pauta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String texto;
    private Integer second;

    private transient boolean possibleToVote = false;

    @OneToMany(mappedBy = "pauta")
    private List<Voto> listVoto;
//    @ManyToMany
//    @JoinTable(name = "associado_pauta",
//            joinColumns = @JoinColumn(name = "id_pauta", foreignKey = @ForeignKey(name = "fk_pautaAssociado")),
//                    inverseJoinColumns = @JoinColumn(name = "id_associado"))
//     private Set<Associado> listAssociado;

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public List<Voto> getListVoto() {
        return listVoto;
    }

    public void setListVoto(List<Voto> listVoto) {
        this.listVoto = listVoto;
    }

//    public Set<Associado> getListAssociado() {
//        return listAssociado;
//    }
//
//    public void setListAssociado(Set<Associado> listAssociado) {
//        this.listAssociado = listAssociado;
//    }

    public boolean isPossibleToVote() {
        return possibleToVote;
    }

    public void setPossibleToVote(boolean possibleToVote) {
        this.possibleToVote = possibleToVote;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, texto, second, possibleToVote, listVoto);
    }
}
