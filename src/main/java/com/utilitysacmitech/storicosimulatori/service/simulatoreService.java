package com.utilitysacmitech.storicosimulatori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utilitysacmitech.storicosimulatori.model.simulatore;
import com.utilitysacmitech.storicosimulatori.repository.simulatoreRepository;

@Service
public class simulatoreService {

    @Autowired
    private simulatoreRepository repository;

    public void saveSimulatore(simulatore simulatore) {
        repository.save(simulatore);
    }

    public simulatore findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<simulatore> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void update(simulatore simulatore) {
        repository.save(simulatore);
    }

}
