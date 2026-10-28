package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementHorsGabaritTest {

    @Test
    void ajouteDixPourcentAuPrixDuComposant() {
        CalculPrix base = (colis, distance) -> 100.0;
        CalculPrix calcul = new SupplementHorsGabarit(base);
        assertEquals(110.0, calcul.calculer(List.of(), 0), 0.001);
    }
}
