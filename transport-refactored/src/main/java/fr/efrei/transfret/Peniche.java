package fr.efrei.transfret;

import java.util.List;

public class Peniche extends Vehicule {

    public Peniche(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax);
    }

    public void partir(int distanceKm) {
        System.out.println("La péniche " + immatriculation + " navigue sur " + distanceKm + " km de voies fluviales");
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        return mode == ModeTransport.FLUVIAL;
    }
}
