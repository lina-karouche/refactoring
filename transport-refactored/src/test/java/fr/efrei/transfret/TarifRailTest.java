package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifRailTest {

    @Test
    void appliqueLeMinimumSiLePrixCalculeEstTropBas() {
        TarifTransport tarif = new TarifRail();
        assertEquals(150.0, tarif.calculerTarifBase(1, 0, 10), 0.001);
    }

    @Test
    void calculeDistanceEtPoidsAuDessusDuMinimum() {
        TarifTransport tarif = new TarifRail();
        assertEquals(807.0, tarif.calculerTarifBase(100, 0, 1000), 0.001);
    }
}
