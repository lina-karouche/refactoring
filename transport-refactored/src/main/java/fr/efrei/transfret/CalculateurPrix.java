package fr.efrei.transfret;

import java.util.List;
import java.util.Map;

public class CalculateurPrix {

    private static final double DIMENSION_MAX_GABARIT_CM = 200;

    private Map<ModeTransport, TarifTransport> tarifs;
    private Map<TypeClient, PolitiqueRemise> remises;

    public CalculateurPrix(Map<ModeTransport, TarifTransport> tarifs, Map<TypeClient, PolitiqueRemise> remises) {
        this.tarifs = tarifs;
        this.remises = remises;
    }

    public double calculerPrixHT(List<Colis> colis, int distance, ModeTransport mode, boolean urgent, boolean assurance, TypeClient typeClient) {
        TarifTransport tarif = tarifs.get(mode);
        if (tarif == null) {
            throw new IllegalArgumentException("Mode inconnu : " + mode);
        }

        boolean fragile = false;
        boolean dangereux = false;
        boolean refrigere = false;
        boolean horsGabarit = false;
        for (Colis c : colis) {
            if (c.getType() == TypeMarchandise.FRAGILE) fragile = true;
            if (c.getType() == TypeMarchandise.DANGEREUX) dangereux = true;
            if (c.getType() == TypeMarchandise.REFRIGERE) refrigere = true;
            if (c.getLongueur() > DIMENSION_MAX_GABARIT_CM || c.getLargeur() > DIMENSION_MAX_GABARIT_CM || c.getHauteur() > DIMENSION_MAX_GABARIT_CM) {
                horsGabarit = true;
            }
        }

        CalculPrix calcul = new TarifDeBase(tarif);
        if (fragile) calcul = new SupplementFragile(calcul);
        if (dangereux) calcul = new SupplementDangereux(calcul);
        if (refrigere) calcul = new SupplementRefrigere(calcul);
        if (horsGabarit) calcul = new SupplementHorsGabarit(calcul);
        if (urgent) calcul = new SupplementUrgence(calcul);

        PolitiqueRemise remise = remises.get(typeClient);
        if (remise != null) {
            calcul = new SupplementRemise(calcul, remise);
        }

        if (assurance) {
            calcul = new SupplementAssurance(calcul);
        }

        return Utils.arrondir(calcul.calculer(colis, distance));
    }
}
