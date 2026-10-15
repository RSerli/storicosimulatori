package com.utilitysacmitech.storicosimulatori.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utilitysacmitech.storicosimulatori.model.simulazioneGenerale;

public interface simulazioneGeneraleRepository extends JpaRepository<simulazioneGenerale, Integer> {

    List<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCaseOrderByCreatedAtDescIdDesc(String nomeImpianto);

    List<simulazioneGenerale> findByNomeTencincoContainingIgnoreCaseOrderByCreatedAtDescIdDesc(String nomeTencinco);

    List<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCaseAndNomeTencincoContainingIgnoreCaseOrderByCreatedAtDescIdDesc(
            String nomeImpianto,
            String nomeTencinco);

}
