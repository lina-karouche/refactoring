package fr.efrei.transfret;

import java.util.List;

public interface CalculPrix {

    double calculer(List<Colis> colis, int distance);
}
