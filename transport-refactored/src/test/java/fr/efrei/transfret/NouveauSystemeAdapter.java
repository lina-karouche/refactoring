package fr.efrei.transfret;

import fr.efrei.transport.contrat.Canal;
import fr.efrei.transport.contrat.ColisDto;
import fr.efrei.transport.contrat.DemandeClient;
import fr.efrei.transport.contrat.DemandeDevis;
import fr.efrei.transport.contrat.DemandeExpedition;
import fr.efrei.transport.contrat.EtatExpedition;
import fr.efrei.transport.contrat.Notification;
import fr.efrei.transport.contrat.Statut;
import fr.efrei.transport.contrat.SystemeTransport;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class NouveauSystemeAdapter implements SystemeTransport {

    private ServiceClient serviceClient;
    private ServiceExpedition serviceExpedition;
    private ClientRepository clientRepository;
    private ExpeditionRepository expeditionRepository;
    private EmailCanal emailCanal;
    private SmsCanal smsCanal;
    private GenerateurFacture generateurFacture;

    @Override
    public void reinitialiser() {
        Flotte flotte = new Flotte();
        clientRepository = new ClientRepositoryMemoire();
        expeditionRepository = new ExpeditionRepositoryMemoire();
        emailCanal = new EmailCanal();
        smsCanal = new SmsCanal();
        Notificateur notificateur = new Notificateur(emailCanal, smsCanal);

        Map<ModeTransport, TarifTransport> tarifs = new HashMap<>();
        tarifs.put(ModeTransport.ROUTE, new TarifRoute());
        tarifs.put(ModeTransport.RAIL, new TarifRail());
        tarifs.put(ModeTransport.MER, new TarifMer());
        tarifs.put(ModeTransport.AIR, new TarifAir());
        tarifs.put(ModeTransport.FLUVIAL, new TarifFluvial());

        Map<TypeClient, PolitiqueRemise> remises = new HashMap<>();
        remises.put(TypeClient.PARTICULIER, new RemiseParticulier());
        remises.put(TypeClient.ENTREPRISE, new RemiseEntreprise());
        remises.put(TypeClient.PREMIUM, new RemisePremium());

        serviceClient = new ServiceClient(clientRepository, emailCanal);
        serviceExpedition = new ServiceExpedition(expeditionRepository, new CalculateurPrix(tarifs, remises),
                new AffectationVehicule(flotte), new ValidateurExpedition(), new EstimateurDelai(), notificateur);
        generateurFacture = new GenerateurFacture();
    }

    @Override
    public Optional<String> creerClient(DemandeClient d) {
        Client client = serviceClient.creerClient(d.nom(), d.email(), d.telephone(), d.adresse(), TypeClient.valueOf(d.type().name()), d.siret());
        return Optional.ofNullable(client).map(Client::getEmail);
    }

    @Override
    public Optional<String> creerExpedition(DemandeExpedition d) {
        Client client = clientRepository.trouverParEmail(d.refClient());
        Expedition expedition = serviceExpedition.creerExpedition(client, versColis(d.colis()), d.villeDepart(),
                d.villeArrivee(), ModeTransport.valueOf(d.mode().name()), d.urgent(), d.assurance());
        return Optional.ofNullable(expedition).map(Expedition::getId);
    }

    @Override
    public Optional<EtatExpedition> consulter(String id) {
        Expedition e = expeditionRepository.trouver(id);
        if (e == null) {
            return Optional.empty();
        }
        int delai = (int) ChronoUnit.DAYS.between(jour(e.getDateCreation()), jour(e.getDateLivraisonPrevue()));
        return Optional.of(new EtatExpedition(e.getId(), Statut.valueOf(e.getStatut().name()), e.getPrixHT(), e.getPrixTTC(),
                delai, e.getVehicule().getImmatriculation(), e.getFraisAnnulation()));
    }

    @Override
    public OptionalDouble devisTTC(DemandeDevis d) {
        double devis = serviceExpedition.calculerDevis(versColis(d.colis()), d.villeDepart(), d.villeArrivee(),
                ModeTransport.valueOf(d.mode().name()), d.urgent(), d.assurance(), TypeClient.valueOf(d.typeClient().name()));
        return devis == -1 ? OptionalDouble.empty() : OptionalDouble.of(devis);
    }

    @Override
    public boolean demarrer(String id) {
        return serviceExpedition.demarrerExpedition(id);
    }

    @Override
    public boolean livrer(String id) {
        return serviceExpedition.livrerExpedition(id);
    }

    @Override
    public boolean annuler(String id) {
        return serviceExpedition.annulerExpedition(id);
    }

    @Override
    public Optional<String> facture(String id) {
        return Optional.ofNullable(expeditionRepository.trouver(id)).map(generateurFacture::genererFacture);
    }

    @Override
    public List<Notification> notifications() {
        List<Notification> notifications = new ArrayList<>();
        for (String[] email : emailCanal.getJournal()) {
            notifications.add(new Notification(Canal.EMAIL, email[0], email[1]));
        }
        for (String[] sms : smsCanal.getJournal()) {
            notifications.add(new Notification(Canal.SMS, sms[0], sms[1]));
        }
        return notifications;
    }

    private static java.time.LocalDate jour(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private static List<Colis> versColis(List<ColisDto> colis) {
        return colis.stream().map(NouveauSystemeAdapter::versColis).collect(Collectors.toCollection(ArrayList::new));
    }

    private static Colis versColis(ColisDto c) {
        return new Colis(c.description(), c.poidsKg(), c.longueurCm(), c.largeurCm(), c.hauteurCm(),
                versTypeMarchandise(c.type()), c.valeurDeclaree());
    }

    private static TypeMarchandise versTypeMarchandise(fr.efrei.transport.contrat.TypeMarchandise type) {
        return switch (type) {
            case STANDARD -> TypeMarchandise.STANDARD;
            case FRAGILE -> TypeMarchandise.FRAGILE;
            case DANGEREUX -> TypeMarchandise.DANGEREUX;
            case REFRIGERE -> TypeMarchandise.REFRIGERE;
        };
    }
}