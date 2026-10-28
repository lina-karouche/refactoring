package fr.efrei.transfret;

import java.util.List;

public class SupplementFragile extends SupplementDecorator {

    private static final double TAUX = 1.15;

    public SupplementFragile(CalculPrix composant) {
        super(composant);
    }

    public double calculer(List<Colis> colis, int distance) {
        return composant.calculer(colis, distance) * TAUX;
    }
}
