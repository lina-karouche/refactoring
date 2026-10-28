package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemiseEntrepriseTest {

    @Test
    void appliqueDixPourcentSousLeSeuil() {
        PolitiqueRemise remise = new RemiseEntreprise();
        assertEquals(3600.0, remise.appliquerRemise(4000), 0.001);
    }

    @Test
    void appliqueQuinzePourcentAuDessusDuSeuil() {
        PolitiqueRemise remise = new RemiseEntreprise();
        assertEquals(5100.0, remise.appliquerRemise(6000), 0.001);
    }
}
