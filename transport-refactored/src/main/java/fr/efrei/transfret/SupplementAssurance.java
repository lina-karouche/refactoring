package fr.efrei.transfret;

import java.util.List;

public class SupplementAssurance extends SupplementDecorator {

    private static final double TAUX_ASSURANCE = 0.02;
    private static final double MINIMUM_ASSURANCE = 15;

    public SupplementAssurance(CalculPrix composant) {
        super(composant);
    }

    public double calculer(List<Colis> colis, int distance) {
        double prix = composant.calculer(colis, distance);
        double valeur = 0;
        for (Colis c : colis) {
            valeur = valeur + c.getValeurDeclaree();
        }
        double montantAssurance = valeur * TAUX_ASSURANCE;
        if (montantAssurance < MINIMUM_ASSURANCE) montantAssurance = MINIMUM_ASSURANCE;
        return prix + montantAssurance;
    }
}
