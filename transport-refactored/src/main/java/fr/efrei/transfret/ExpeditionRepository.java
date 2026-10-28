package fr.efrei.transfret;

import java.util.List;

public interface ExpeditionRepository {

    void sauvegarder(Expedition expedition);

    void mettreAJourStatut(Expedition expedition);

    Expedition trouver(String id);

    List<Expedition> tous();
}
