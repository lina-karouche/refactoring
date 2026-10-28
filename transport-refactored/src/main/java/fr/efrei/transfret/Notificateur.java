package fr.efrei.transfret;

public class Notificateur {

    private CanalNotification canalEmail;
    private CanalNotification canalSms;

    public Notificateur(CanalNotification canalEmail, CanalNotification canalSms) {
        this.canalEmail = canalEmail;
        this.canalSms = canalSms;
    }

    public void notifier(Client client, String sujet, String corps, String sms) {
        canalEmail.envoyer(client.getEmail(), sujet, corps);
        if (client.getType() == TypeClient.PREMIUM) {
            canalSms.envoyer(client.getTelephone(), sujet, sms);
        }
    }
}
