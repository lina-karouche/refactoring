package fr.efrei.transfret;

public class RemiseEntreprise implements PolitiqueRemise {

    private static final double SEUIL = 5000;
    private static final double TAUX_SEUIL_DEPASSE = 0.85;
    private static final double TAUX_SEUIL_NON_ATTEINT = 0.90;

    public double appliquerRemise(double prix) {
        if (prix > SEUIL) {
            return prix * TAUX_SEUIL_DEPASSE;
        }
        return prix * TAUX_SEUIL_NON_ATTEINT;
    }
}
