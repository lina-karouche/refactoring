package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementDangereuxTest {

    @Test
    void ajouteTrenteCentPourcentAuPrixDuComposant() {
        CalculPrix base = (colis, distance) -> 100.0;
        CalculPrix calcul = new SupplementDangereux(base);
        assertEquals(130.0, calcul.calculer(List.of(), 0), 0.001);
    }
}
