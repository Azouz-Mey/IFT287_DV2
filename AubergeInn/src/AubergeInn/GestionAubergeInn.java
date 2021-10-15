package AubergeInn;


import Tables.*;
import Transactions.*;

import java.sql.SQLException;

/**
 * Système de gestion d'une Auberge-Inn
 */

public class GestionAubergeInn {

    private Connexion cx;
    private TableClient client;
    private GestionClient gestionClient;
    private TableChambre chambre;
    private GestionChambre gestionChambre;
    private TableCommodite commodite;
    private GestionCommodite gestionCommodite;
    private TableCommodChambre commodChambre;
    private GestionCommodChambre gestionCommodChambre;
    private TableReservation reservation;
    private GestionReservation gestionReservation;
    private TableDate date;
    private GestionDate gestionDate;


    /**
     * Ouvre une connexion avec la BD relationnelle et alloue les gestionnaires
     * de transactions et de tables.
     *
     * @param serveur SQL
     * @param bd nom de la bade de données
     * @param user user id pour établir une connexion avec le serveur SQL
     * @param password mot de passe pour le user id
     */
    public GestionAubergeInn(String serveur, String bd, String user, String password)
            throws IFT287Exception, SQLException
    {
        // Allocation des objets pour le traitement des transactions
        cx = new Connexion(serveur, bd, user, password);
        client = new TableClient(cx);
        gestionClient = new GestionClient(client);
        chambre = new TableChambre(cx);
        gestionChambre = new GestionChambre(chambre);
        commodite = new TableCommodite(cx);
        gestionCommodite = new GestionCommodite(commodite);
        commodChambre = new TableCommodChambre(cx);
        gestionCommodChambre = new GestionCommodChambre(commodChambre, commodite,chambre );
        date = new TableDate(cx);
        gestionDate = new GestionDate(date);
        reservation = new TableReservation(cx);
        gestionReservation = new GestionReservation(reservation, client,chambre );
    }
    public GestionClient getGestionClient() {
        return gestionClient;
    }
    public GestionChambre getGestionChambre() {
        return gestionChambre;
    }
    public GestionCommodite getGestionCommodite() {
        return gestionCommodite;
    }
    public GestionCommodChambre getGestionCommodChambre() {
        return gestionCommodChambre;
    }
    public GestionDate getGestionDate (){ return gestionDate;}
    public GestionReservation getGestionReservation() { return gestionReservation; }

}
