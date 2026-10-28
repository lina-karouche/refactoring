package fr.efrei.transfret;

import java.util.List;

public class Train extends Vehicule {

    public Train(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax);
    }

    public void partir(int distanceKm) {
        System.out.println("Le train " + immatriculation + " circule sur " + distanceKm + " km de voies ferrées");
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        return mode == ModeTransport.RAIL;
    }

    @Override
    public void faireLePlein() {
        System.out.println("Train électrique : pas de plein nécessaire pour " + immatriculation);
    }
}
