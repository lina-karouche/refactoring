package fr.efrei.transfret;

public class TarifRail implements TarifTransport {

    private static final double TARIF_KM = 0.8;
    private static final double TARIF_KG = 0.07;
    private static final double MINIMUM = 150;

    public double calculerTarifBase(double poidsKg, double volumeM3, int distanceKm) {
        double prix = distanceKm * TARIF_KM + poidsKg * TARIF_KG;
        return Math.max(prix, MINIMUM);
    }
}
