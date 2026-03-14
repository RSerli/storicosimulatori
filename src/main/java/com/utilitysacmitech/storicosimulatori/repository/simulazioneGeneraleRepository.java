package com.utilitysacmitech.storicosimulatori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utilitysacmitech.storicosimulatori.model.simulazioneGenerale;

public interface simulazioneGeneraleRepository extends JpaRepository<simulazioneGenerale, Integer> {

    List<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCaseOrderByCreatedAtDesc(String nomeImpianto);

    List<simulazioneGenerale> findByNomeTencincoContainingIgnoreCaseOrderByCreatedAtDesc(String nomeTencinco);

    List<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCaseAndNomeTencincoContainingIgnoreCaseOrderByCreatedAtDesc(
            String nomeImpianto,
            String nomeTencinco);

}
