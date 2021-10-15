package Transactions;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import Tables.TableChambre;
import Tables.TableClient;
import Tables.TableReservation;

import java.sql.Date;
import java.sql.SQLException;

public class GestionReservation {

    private final TableChambre chambre;
    private final TableClient client;
    private final TableReservation reservation;
    private final Connexion cx;

    /**
     * Creation d'une instance
     */
    public GestionReservation(TableReservation reservation, TableClient client, TableChambre chambre) throws IFT287Exception {
        this.cx = chambre.getConnexion();

        this.chambre = chambre;
        this.client = client;
        this.reservation = reservation;
    }

    public void reserver(int idclient, int idChambre, Date datedebut, Date datefin) throws SQLException, IFT287Exception {
        try {
            //Verifier si la chambre est deja reservee ou non
            if (reservation.chambreReservee(idChambre, datedebut, datefin)) {
                throw new IFT287Exception("Impossible : La chambre est deja reservee pendant les dates choisis");
            }
            //Verifier si la chambre existe ou non
            if (!chambre.existe(idChambre)) {
                throw new IFT287Exception("Impossible : La chambre n'existe pas");
            }

            if (!client.existe(idclient)) {
                throw new IFT287Exception("Impossible : Le client n'existe pas");
            }
            if (datedebut.after(datefin))
            {
                throw new IFT287Exception("Impossible : La date de début est après la date de fin");
            }
            //double prix = chambre.getPrixChambre(idChambre);

            reservation.reserver(idclient, idChambre, datedebut, datefin);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}
