package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemiseParticulierTest {

    @Test
    void neModifiePasLePrix() {
        PolitiqueRemise remise = new RemiseParticulier();
        assertEquals(1000.0, remise.appliquerRemise(1000), 0.001);
    }
}
