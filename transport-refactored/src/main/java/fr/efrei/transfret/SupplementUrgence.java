package fr.efrei.transfret;

import java.util.List;

public class SupplementUrgence extends SupplementDecorator {

    private static final double TAUX = 1.5;

    public SupplementUrgence(CalculPrix composant) {
        super(composant);
    }

    public double calculer(List<Colis> colis, int distance) {
        return composant.calculer(colis, distance) * TAUX;
    }
}
