package fr.efrei.transfret;

import java.util.List;

public class CalculateurPrix {

    private static final double TARIF_KM_ROUTE = 1.2;
    private static final double TARIF_KG_ROUTE = 0.10;

    private static final double TARIF_KM_RAIL = 0.8;
    private static final double TARIF_KG_RAIL = 0.07;
    private static final double MINIMUM_RAIL = 150;

    private static final double TARIF_KM_MER = 0.5;
    private static final double TARIF_KG_MER = 0.03;
    private static final double MINIMUM_MER = 500;

    private static final double TARIF_KM_AIR = 0.6;
    private static final double TARIF_KG_AIR = 4.5;
    private static final double RATIO_POIDS_VOLUMETRIQUE_AIR = 167;

    private static final double SUPPLEMENT_FRAGILE = 1.15;
    private static final double SUPPLEMENT_DANGEREUX = 1.30;
    private static final double SUPPLEMENT_REFRIGERE = 1.20;
    private static final double SUPPLEMENT_URGENT = 1.5;

    private static final double SEUIL_REMISE_ENTREPRISE = 5000;
    private static final double REMISE_ENTREPRISE_SEUIL_DEPASSE = 0.85;
    private static final double REMISE_ENTREPRISE_SEUIL_NON_ATTEINT = 0.90;
    private static final double REMISE_PREMIUM = 0.80;

    private static final double TAUX_ASSURANCE = 0.02;
    private static final double MINIMUM_ASSURANCE = 15;

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        double poids = 0;
        double volume = 0;
        double valeur = 0;
        boolean fragile = false;
        boolean dangereux = false;
        boolean refrigere = false;
        for (Colis c : colis) {
            poids = poids + c.getPoids();
            volume = volume + c.getVolume();
            valeur = valeur + c.getValeurDeclaree();
            if (c.getType() == TypeMarchandise.FRAGILE) fragile = true;
            if (c.getType() == TypeMarchandise.DANGEREUX) dangereux = true;
            if (c.getType() == TypeMarchandise.REFRIGERE) refrigere = true;
        }

        // tarif de base selon le mode
        double prix;
        switch (mode) {
            case ROUTE:
                prix = distance * TARIF_KM_ROUTE + poids * TARIF_KG_ROUTE;
                break;
            case RAIL:
                prix = distance * TARIF_KM_RAIL + poids * TARIF_KG_RAIL;
                if (prix < MINIMUM_RAIL) prix = MINIMUM_RAIL;
                break;
            case MER:
                prix = distance * TARIF_KM_MER + poids * TARIF_KG_MER;
                if (prix < MINIMUM_MER) prix = MINIMUM_MER;
                break;
            case AIR:
                double poidsTaxable = poids;
                if (volume * RATIO_POIDS_VOLUMETRIQUE_AIR > poids) {
                    poidsTaxable = volume * RATIO_POIDS_VOLUMETRIQUE_AIR;
                }
                prix = distance * TARIF_KM_AIR + poidsTaxable * TARIF_KG_AIR;
                break;
            default:
                throw new IllegalArgumentException("Mode inconnu : " + mode);
        }

        // suppléments
        if (fragile) prix = prix * SUPPLEMENT_FRAGILE;
        if (dangereux) prix = prix * SUPPLEMENT_DANGEREUX;
        if (refrigere) prix = prix * SUPPLEMENT_REFRIGERE;
        if (urgent) prix = prix * SUPPLEMENT_URGENT;

        // remise client
        if (typeClient == TypeClient.ENTREPRISE) {
            if (prix > SEUIL_REMISE_ENTREPRISE) {
                prix = prix * REMISE_ENTREPRISE_SEUIL_DEPASSE;
            } else {
                prix = prix * REMISE_ENTREPRISE_SEUIL_NON_ATTEINT;
            }
        } else if (typeClient == TypeClient.PREMIUM) {
            prix = prix * REMISE_PREMIUM;
        }

        // assurance
        if (assurance) {
            double montantAssurance = valeur * TAUX_ASSURANCE;
            if (montantAssurance < MINIMUM_ASSURANCE) montantAssurance = MINIMUM_ASSURANCE;
            prix = prix + montantAssurance;
        }
        return Utils.arrondir(prix);
    }
}
