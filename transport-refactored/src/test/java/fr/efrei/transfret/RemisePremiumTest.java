package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemisePremiumTest {

    @Test
    void appliqueVingtPourcentDeRemise() {
        PolitiqueRemise remise = new RemisePremium();
        assertEquals(800.0, remise.appliquerRemise(1000), 0.001);
    }
}
