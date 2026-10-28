package fr.efrei.transfret;

import java.util.List;

public class SupplementDangereux extends SupplementDecorator {

    private static final double TAUX = 1.30;

    public SupplementDangereux(CalculPrix composant) {
        super(composant);
    }

    public double calculer(List<Colis> colis, int distance) {
        return composant.calculer(colis, distance) * TAUX;
    }
}
