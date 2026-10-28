package fr.efrei.transfret;

import java.util.List;

public class Camion extends Vehicule {

    protected boolean adr; // certification matières dangereuses

    public Camion(String immatriculation, double capaciteMax, boolean adr) {
        super(immatriculation, capaciteMax);
        this.adr = adr;
    }

    public void partir(int distanceKm) {
        System.out.println("Le camion " + immatriculation + " roule sur " + distanceKm + " km");
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        if (mode != ModeTransport.ROUTE) return false;
        if (ColisUtils.contient(colis, TypeMarchandise.REFRIGERE)) return false;
        if (ColisUtils.contient(colis, TypeMarchandise.DANGEREUX) && !adr) return false;
        return true;
    }
}
