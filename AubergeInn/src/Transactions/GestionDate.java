package Transactions;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import Tables.TableDate;
import Tables.TableReservation;

import java.sql.Date;
import java.sql.SQLException;

public class GestionDate {
    private final TableDate date;
    private final Connexion cx;

    public GestionDate(TableDate date){
        this.cx = date.getConnexion();
        this.date = date;
    }

    public void ajouterDate(Date datedebut, Date datefin) throws SQLException, IFT287Exception {
        try {
            //Verifier si la chambre est deja reservee ou non
            if (date.existeDate(datedebut, datefin)) {
                throw new IFT287Exception("Impossible : Les dates existent déjà");
            }
            if (datedebut.after(datefin)) {
                throw new IFT287Exception("Impossible : La date de fin est avant celle du début");
            }
            date.ajouterDate(datedebut, datefin);

            // Commit
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }


    /**
     * Suppression de dates
     */
    public int supprimerDate(Date datedebut, Date datefin) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            //Vérification de l'existence du client
            if (!date.existeDate(datedebut, datefin)) {
                throw new IFT287Exception("Ce couple de date n'existe pas: ");
            }

            //Suppression du client
            int resultatSuppression = date.SupprimerDate(datedebut,datefin);

            // Commit
            cx.commit();
            return resultatSuppression;

        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

}
