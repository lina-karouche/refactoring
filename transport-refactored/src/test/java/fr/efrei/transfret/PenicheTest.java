package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PenicheTest {

    @Test
    void nePrendEnChargeQueLeModeFluvial() {
        Peniche peniche = new Peniche("PEN-0001", 2500000);
        assertTrue(peniche.peutPrendreEnCharge(List.of(), ModeTransport.FLUVIAL));
        assertFalse(peniche.peutPrendreEnCharge(List.of(), ModeTransport.ROUTE));
    }
}
