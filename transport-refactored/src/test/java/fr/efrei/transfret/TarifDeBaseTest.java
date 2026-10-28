package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TarifDeBaseTest {

    @Test
    void delegueAuTarifDuModeAvecLePoidsTotalDesColis() {
        CalculPrix calcul = new TarifDeBase(new TarifRoute());
        List<Colis> colis = List.of(new Colis("Carton", 50, 10, 10, 10, TypeMarchandise.STANDARD, 100));
        assertEquals(125.0, calcul.calculer(colis, 100), 0.001);
    }
}
