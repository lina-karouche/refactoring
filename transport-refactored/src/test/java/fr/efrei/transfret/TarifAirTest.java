package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifAirTest {

    @Test
    void utiliseLePoidsReelQuandIlDepasseLePoidsVolumetrique() {
        TarifTransport tarif = new TarifAir();
        assertEquals(1050.0, tarif.calculerTarifBase(100, 0.1, 1000), 0.001);
    }

    @Test
    void utiliseLePoidsVolumetriqueQuandIlDepasseLePoidsReel() {
        TarifTransport tarif = new TarifAir();
        assertEquals(811.5, tarif.calculerTarifBase(10, 1, 100), 0.001);
    }
}
