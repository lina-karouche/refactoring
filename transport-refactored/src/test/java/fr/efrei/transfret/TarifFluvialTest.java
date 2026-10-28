package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifFluvialTest {

    @Test
    void calculeDistanceEtPoidsAuDessusDuMinimum() {
        TarifTransport tarif = new TarifFluvial();
        assertEquals(440.0, tarif.calculerTarifBase(2000, 0, 1000), 0.001);
    }

    @Test
    void appliqueLeMinimumSiLePrixCalculeEstTropBas() {
        TarifTransport tarif = new TarifFluvial();
        assertEquals(300.0, tarif.calculerTarifBase(1, 0, 10), 0.001);
    }
}
