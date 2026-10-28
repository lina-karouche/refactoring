package fr.efrei.transfret;

public class TarifAir implements TarifTransport {

    private static final double TARIF_KM = 0.6;
    private static final double TARIF_KG = 4.5;
    private static final double RATIO_POIDS_VOLUMETRIQUE = 167;

    public double calculerTarifBase(double poidsKg, double volumeM3, int distanceKm) {
        double poidsTaxable = poidsKg;
        if (volumeM3 * RATIO_POIDS_VOLUMETRIQUE > poidsKg) {
            poidsTaxable = volumeM3 * RATIO_POIDS_VOLUMETRIQUE;
        }
        return distanceKm * TARIF_KM + poidsTaxable * TARIF_KG;
    }
}
