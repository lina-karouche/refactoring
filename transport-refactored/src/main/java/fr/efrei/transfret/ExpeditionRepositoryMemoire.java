package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpeditionRepositoryMemoire implements ExpeditionRepository {

    private Map<String, Expedition> expeditions = new HashMap<String, Expedition>();

    public void sauvegarder(Expedition expedition) {
        System.out.println("[SQL] INSERT INTO expedition (id, client, depart, arrivee, mode, statut, prix_ttc) VALUES ('"
                + expedition.getId() + "', '" + expedition.getClient().getEmail() + "', '" + expedition.getVilleDepart()
                + "', '" + expedition.getVilleArrivee() + "', '" + expedition.getMode() + "', '" + expedition.getStatut()
                + "', " + expedition.getPrixTTC() + ")");
        expeditions.put(expedition.getId(), expedition);
    }

    public void mettreAJourStatut(Expedition expedition) {
        System.out.println("[SQL] UPDATE expedition SET statut = '" + expedition.getStatut() + "' WHERE id = '" + expedition.getId() + "'");
    }

    public Expedition trouver(String id) {
        System.out.println("[SQL] SELECT * FROM expedition WHERE id = '" + id + "'");
        return expeditions.get(id);
    }

    public List<Expedition> tous() {
        System.out.println("[SQL] SELECT * FROM expedition");
        return new ArrayList<Expedition>(expeditions.values());
    }
}
