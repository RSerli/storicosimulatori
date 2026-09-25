package com.utilitysacmitech.storicosimulatori.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.utilitysacmitech.storicosimulatori.model.simulazioneGenerale;
import com.utilitysacmitech.storicosimulatori.repository.simulazioneGeneraleRepository;

@Service
public class simulazioneService {

    private final simulazioneGeneraleRepository repository;

    public simulazioneService(simulazioneGeneraleRepository repository) {
        this.repository = repository;
    }

    public void saveSimulazione(simulazioneGenerale simulazione) {
        repository.save(simulazione);
    }

    public simulazioneGenerale findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<simulazioneGenerale> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Iterable<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCase(String nomeImpianto) {
        return repository.findByNomeImpiantoContainingIgnoreCaseOrderByCreatedAtDesc(nomeImpianto);
    }

    public Iterable<simulazioneGenerale> findByNomeTencincoContainingIgnoreCase(String nomeTencinco) {
        return repository.findByNomeTencincoContainingIgnoreCaseOrderByCreatedAtDesc(nomeTencinco);
    }

    public Iterable<simulazioneGenerale> findByNomeImpiantoContainingIgnoreCaseAndNomeTencincoContainingIgnoreCase(
            String nomeImpianto,
            String nomeTencinco) {
        return repository.findByNomeImpiantoContainingIgnoreCaseAndNomeTencincoContainingIgnoreCaseOrderByCreatedAtDesc(nomeImpianto,
                nomeTencinco);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void update(simulazioneGenerale simulazione) {
        repository.save(simulazione);
    }

}
