package fr.efrei.transfret;

import java.util.List;

public class SupplementRemise extends SupplementDecorator {

    private PolitiqueRemise politiqueRemise;

    public SupplementRemise(CalculPrix composant, PolitiqueRemise politiqueRemise) {
        super(composant);
        this.politiqueRemise = politiqueRemise;
    }

    public double calculer(List<Colis> colis, int distance) {
        return politiqueRemise.appliquerRemise(composant.calculer(colis, distance));
    }
}
