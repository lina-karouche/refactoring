package fr.efrei.transfret;

import java.util.ArrayList;
import java.util.List;

public class SmsCanal implements CanalNotification {

    private static final int LONGUEUR_MAX_SMS = 160;

    private List<String[]> journal = new ArrayList<String[]>();

    public void envoyer(String destinataire, String sujet, String corps) {
        String message = corps;
        if (message.length() > LONGUEUR_MAX_SMS) {
            message = message.substring(0, LONGUEUR_MAX_SMS - 3) + "...";
        }
        System.out.println("[SMS] -> " + destinataire + " : " + message);
        journal.add(new String[]{destinataire, message});
    }

    public List<String[]> getJournal() {
        return journal;
    }
}
