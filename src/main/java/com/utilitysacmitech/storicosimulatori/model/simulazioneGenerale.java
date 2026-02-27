package com.utilitysacmitech.storicosimulatori.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="simulazione")
public class simulazioneGenerale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @SuppressWarnings("FieldMayBeFinal")
    private LocalDate createdAt = LocalDate.now();

    @NotBlank
    @NotNull (message="Campo obbligatorio")
    private String nomeImpianto;

    @NotBlank
    @NotNull (message="Campo obbligatorio")
    private String nomeTencinco;

    @ManyToOne
    @JoinColumn(name="simulatore_id")
    private simulatore simulatoreAssociatoImpianto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getNomeImpianto() {
        return nomeImpianto;
    }

    public void setNomeImpianto(String nomeImpianto) {
        this.nomeImpianto = nomeImpianto;
    }

    public String getNomeTencinco() {
        return nomeTencinco;
    }

    public void setNomeTencinco(String nomeTencinco) {
        this.nomeTencinco = nomeTencinco;
    }

    public simulatore getSimulatoreAssociatoImpianto() {
        return simulatoreAssociatoImpianto;
    }

    public void setSimulatoreAssociatoImpianto(simulatore simulatoreAssociatoImpianto) {
        this.simulatoreAssociatoImpianto = simulatoreAssociatoImpianto;
    }

    
}
