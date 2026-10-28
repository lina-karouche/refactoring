package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementRemiseTest {

    @Test
    void delegueLaRemiseALaPolitiqueFournie() {
        CalculPrix base = (colis, distance) -> 1000.0;
        CalculPrix calcul = new SupplementRemise(base, new RemisePremium());
        assertEquals(800.0, calcul.calculer(List.of(), 0), 0.001);
    }
}
