package com.utilitysacmitech.storicosimulatori.service;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    public boolean isServerReachable(String ipAddress) {
        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            return address.isReachable(3000);
        } catch (Exception e) {
            return false;
        }
    }

    public void checkAllServersReachable() {
        Iterable<simulatore> simulatori = findAll();
        for (simulatore sim : simulatori) {
            boolean reachable = isServerReachable(sim.getIpString());
            sim.setServerAcceso(reachable);
            update(sim);
        }
    }

}
