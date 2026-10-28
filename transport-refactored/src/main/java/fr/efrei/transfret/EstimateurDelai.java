package fr.efrei.transfret;

public class EstimateurDelai {

    private static final double VITESSE_ROUTE_KM_H = 70.0;
    private static final int DIVISEUR_HEURES_URGENT_ROUTE = 18;
    private static final int DIVISEUR_HEURES_NORMAL_ROUTE = 9;

    private static final double DISTANCE_PAR_JOUR_RAIL = 1440.0;

    private static final double DISTANCE_PAR_JOUR_MER = 720.0;
    private static final int JOURS_SUPPLEMENTAIRES_MER = 2;

    private static final int DELAI_AIR_STANDARD = 1;
    private static final int DELAI_AIR_LONGUE_DISTANCE = 2;
    private static final int DISTANCE_LONGUE_AIR = 3000;

    private static final double DISTANCE_PAR_JOUR_FLUVIAL = 360.0;

    // retourne le délai de livraison en jours
    public int calculerDelai(int distance, ModeTransport mode, boolean urgent) {
        int jours = 0;
        switch (mode) {
            case ROUTE:
                double heures = distance / VITESSE_ROUTE_KM_H;
                if (urgent) {
                    jours = (int) Math.ceil(heures / DIVISEUR_HEURES_URGENT_ROUTE);
                } else {
                    jours = (int) Math.ceil(heures / DIVISEUR_HEURES_NORMAL_ROUTE);
                }
                break;
            case RAIL:
                jours = (int) Math.ceil(distance / DISTANCE_PAR_JOUR_RAIL) + 1;
                if (urgent) jours = jours - 1;
                break;
            case MER:
                jours = (int) Math.ceil(distance / DISTANCE_PAR_JOUR_MER) + JOURS_SUPPLEMENTAIRES_MER;
                break;
            case AIR:
                jours = DELAI_AIR_STANDARD;
                if (distance > DISTANCE_LONGUE_AIR) jours = DELAI_AIR_LONGUE_DISTANCE;
                if (urgent) jours = DELAI_AIR_STANDARD;
                break;
            case FLUVIAL:
                jours = (int) Math.ceil(distance / DISTANCE_PAR_JOUR_FLUVIAL);
                break;
        }
        if (jours < 1) jours = 1;
        return jours;
    }
}
