package fr.efrei.transfret;

import java.util.List;

/**
 * Interface commune à tous les véhicules de la flotte.
 */
public interface IVehicule {

    String getImmatriculation();

    double getCapaciteMax();

    double getChargeActuelle();

    boolean estDisponible();

    void setDisponible(boolean disponible);

    void charger(Colis colis);

    void decharger(Colis colis);

    void partir(int distanceKm);

    void faireLePlein();

    boolean peutPrendreEnCharge(List<Colis> colis, ModeTransport mode);
}
