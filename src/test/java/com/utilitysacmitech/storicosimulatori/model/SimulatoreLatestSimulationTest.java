package com.utilitysacmitech.storicosimulatori.model;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class SimulatoreLatestSimulationTest {

    @Test
    void returnsSimulationWithHighestIdWhenCreationTimestampsMatch() {
        LocalDateTime createdAt = LocalDateTime.of(2026, 10, 15, 10, 0);
        simulazioneGenerale first = new simulazioneGenerale();
        first.setId(1);
        ReflectionTestUtils.setField(first, "createdAt", createdAt);

        simulazioneGenerale second = new simulazioneGenerale();
        second.setId(2);
        ReflectionTestUtils.setField(second, "createdAt", createdAt);

        simulatore simulator = new simulatore();
        simulator.setIsFree(false);
        simulator.setSimulazioniAssociate(List.of(first, second));

        assertSame(second, simulator.getUltimaSimulazione());
    }
}
