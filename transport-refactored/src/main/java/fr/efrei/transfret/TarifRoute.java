package fr.efrei.transfret;

public class TarifRoute implements TarifTransport {

    private static final double TARIF_KM = 1.2;
    private static final double TARIF_KG = 0.10;

    public double calculerTarifBase(double poidsKg, double volumeM3, int distanceKm) {
        return distanceKm * TARIF_KM + poidsKg * TARIF_KG;
    }
}
