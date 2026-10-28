package fr.efrei.transfret;

public class RemisePremium implements PolitiqueRemise {

    private static final double TAUX = 0.80;

    public double appliquerRemise(double prix) {
        return prix * TAUX;
    }
}
