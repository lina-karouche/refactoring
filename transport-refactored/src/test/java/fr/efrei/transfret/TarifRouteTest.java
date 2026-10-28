package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifRouteTest {

    @Test
    void calculeDistanceEtPoids() {
        TarifTransport tarif = new TarifRoute();
        assertEquals(125.0, tarif.calculerTarifBase(50, 0, 100), 0.001);
    }
}
