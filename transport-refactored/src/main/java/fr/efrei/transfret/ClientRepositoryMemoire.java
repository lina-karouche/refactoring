package fr.efrei.transfret;

import java.util.HashMap;
import java.util.Map;

public class ClientRepositoryMemoire implements ClientRepository {

    private Map<String, Client> clients = new HashMap<String, Client>();

    public void sauvegarder(Client client) {
        System.out.println("[SQL] INSERT INTO client (nom, email, telephone, type) VALUES ('"
                + client.getNom() + "', '" + client.getEmail() + "', '" + client.getTelephone() + "', '" + client.getType() + "')");
        clients.put(client.getEmail(), client);
    }

    public Client trouverParEmail(String email) {
        return clients.get(email);
    }
}
