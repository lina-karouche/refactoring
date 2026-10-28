package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementFragileTest {

    @Test
    void ajouteQuinzePourcentAuPrixDuComposant() {
        CalculPrix base = (colis, distance) -> 100.0;
        CalculPrix calcul = new SupplementFragile(base);
        assertEquals(115.0, calcul.calculer(List.of(), 0), 0.001);
    }
}
