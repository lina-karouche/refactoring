package fr.efrei.transfret;

import java.util.List;

public class CamionFrigorifique extends Camion {

    private static final double TEMPERATURE_REFRIGERATION = 4;

    public CamionFrigorifique(String immatriculation, double capaciteMax) {
        super(immatriculation, capaciteMax, false);
    }

    @Override
    public void partir(int distanceKm) {
        super.partir(distanceKm);
        activerRefrigeration(TEMPERATURE_REFRIGERATION);
    }

    @Override
    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        if (mode != ModeTransport.ROUTE) return false;
        return ColisUtils.contient(colis, TypeMarchandise.REFRIGERE);
    }

    public void activerRefrigeration(double temperature) {
        System.out.println("Réfrigération activée à " + temperature + "°C sur " + immatriculation);
    }
}
