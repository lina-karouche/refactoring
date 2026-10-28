package fr.efrei.transfret;

public class TarifFluvial implements TarifTransport {

    private static final double TARIF_KM = 0.40;
    private static final double TARIF_KG = 0.02;
    private static final double MINIMUM = 300;

    public double calculerTarifBase(double poidsKg, double volumeM3, int distanceKm) {
        double prix = distanceKm * TARIF_KM + poidsKg * TARIF_KG;
        return Math.max(prix, MINIMUM);
    }
}
