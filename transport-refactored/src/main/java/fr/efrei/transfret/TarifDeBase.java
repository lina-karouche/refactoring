package fr.efrei.transfret;

import java.util.List;

public class TarifDeBase implements CalculPrix {

    private TarifTransport tarif;

    public TarifDeBase(TarifTransport tarif) {
        this.tarif = tarif;
    }

    public double calculer(List<Colis> colis, int distance) {
        double poids = 0;
        double volume = 0;
        for (Colis c : colis) {
            poids = poids + c.getPoids();
            volume = volume + c.getVolume();
        }
        return tarif.calculerTarifBase(poids, volume, distance);
    }
}
