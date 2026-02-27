package com.utilitysacmitech.storicosimulatori.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="simulatore")
public class simulatore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull (message="Campo obbligatorio")
    private String nomeServer;

    @NotBlank
    @NotNull (message="Campo obbligatorio")
    private String ipString;

    @NotBlank
    @NotNull (message="Campo obbligatorio")
    private String sistemaOperativo;


    @OneToMany(mappedBy="simulatoreAssociatoImpianto")
    private List<simulazioneGenerale> simulazioniAssociate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeServer() {
        return nomeServer;
    }

    public void setNomeServer(String nomeServer) {
        this.nomeServer = nomeServer;
    }

    public String getIpString() {
        return ipString;
    }

    public void setIpString(String ipString) {
        this.ipString = ipString;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public List<simulazioneGenerale> getSimulazioniAssociate() {
        return simulazioniAssociate;
    }

    public void setSimulazioniAssociate(List<simulazioneGenerale> simulazioniAssociate) {
        this.simulazioniAssociate = simulazioniAssociate;
    }

    
}
