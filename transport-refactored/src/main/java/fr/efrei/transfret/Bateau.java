package fr.efrei.transfret;

import java.util.List;

public class Bateau extends Vehicule {

    private static final double KM_PAR_MILLE_NAUTIQUE = 1.852;
    private static final double TEMPERATURE_REFRIGERATION = 4;

    private boolean conteneursRefrigeres;

    public Bateau(String immatriculation, double capaciteMax, boolean conteneursRefrigeres) {
        super(immatriculation, capaciteMax);
        this.conteneursRefrigeres = conteneursRefrigeres;
    }

    public void partir(int distanceKm) {
        int milles = (int) (distanceKm / KM_PAR_MILLE_NAUTIQUE);
        System.out.println("Le navire " + immatriculation + " navigue sur " + milles + " milles nautiques");
        if (ColisUtils.contient(chargement, TypeMarchandise.REFRIGERE)) {
            activerRefrigeration(TEMPERATURE_REFRIGERATION);
        }
    }

    public boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode) {
        if (mode != ModeTransport.MER) return false;
        if (ColisUtils.contient(colis, TypeMarchandise.REFRIGERE) && !conteneursRefrigeres) return false;
        return true;
    }

    public void activerRefrigeration(double temperature) {
        System.out.println("Conteneurs frigorifiques réglés à " + temperature + "°C sur " + immatriculation);
    }

    public boolean aConteneursRefrigeres() {
        return conteneursRefrigeres;
    }
}
