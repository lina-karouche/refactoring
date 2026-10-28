package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifMerTest {

    @Test
    void appliqueLeMinimumSiLePrixCalculeEstTropBas() {
        TarifTransport tarif = new TarifMer();
        assertEquals(500.0, tarif.calculerTarifBase(1, 0, 10), 0.001);
    }

    @Test
    void calculeDistanceEtPoidsAuDessusDuMinimum() {
        TarifTransport tarif = new TarifMer();
        assertEquals(1030.0, tarif.calculerTarifBase(1000, 0, 2000), 0.001);
    }
}
