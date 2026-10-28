package fr.efrei.transfret;

public class TarifMer implements TarifTransport {

    private static final double TARIF_KM = 0.5;
    private static final double TARIF_KG = 0.03;
    private static final double MINIMUM = 500;

    public double calculerTarifBase(double poidsKg, double volumeM3, int distanceKm) {
        double prix = distanceKm * TARIF_KM + poidsKg * TARIF_KG;
        return Math.max(prix, MINIMUM);
    }
}
