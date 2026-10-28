package fr.efrei.transfret;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplementAssuranceTest {

    @Test
    void ajouteDeuxPourcentDeLaValeurDeclaree() {
        CalculPrix base = (colis, distance) -> 100.0;
        CalculPrix calcul = new SupplementAssurance(base);
        List<Colis> colis = List.of(new Colis("Carton", 10, 10, 10, 10, TypeMarchandise.STANDARD, 2000));
        assertEquals(140.0, calcul.calculer(colis, 0), 0.001);
    }

    @Test
    void appliqueLeMinimumSiLaValeurDeclareeEstFaible() {
        CalculPrix base = (colis, distance) -> 100.0;
        CalculPrix calcul = new SupplementAssurance(base);
        List<Colis> colis = List.of(new Colis("Carton", 10, 10, 10, 10, TypeMarchandise.STANDARD, 100));
        assertEquals(115.0, calcul.calculer(colis, 0), 0.001);
    }
}
