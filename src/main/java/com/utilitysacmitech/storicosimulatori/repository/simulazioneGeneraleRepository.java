package com.utilitysacmitech.storicosimulatori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utilitysacmitech.storicosimulatori.model.simulazioneGenerale;

public interface simulazioneGeneraleRepository extends JpaRepository<simulazioneGenerale, Integer> {

    List<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCase(String nomeImpianto);

    List<simulazioneGenerale> findByNomeTencincoContainingIgnoreCase(String nomeTencinco);

    List<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCaseAndNomeTencincoContainingIgnoreCase(
            String nomeImpianto,
            String nomeTencinco);

}
