package Transactions;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import Tables.TableClient;
import Tables.TableCommodite;

import java.sql.SQLException;

public class GestionCommodite {

    private TableCommodite commodite;
    private Connexion cx;

    /**
     * Creation d'une instance
     */
    public GestionCommodite(TableCommodite commodite) throws IFT287Exception
    {
        this.cx = commodite.getConnexion();
        // if (client.getConnexion() != reservation.getConnexion())
        // throw new BiblioException("Les instances de livre et de reservation n'utilisent pas la même connexion au serveur");
        this.commodite = commodite;
        //this.reservation = reservation;
    }

    /**
     * Ajout d'un nouveau client dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void ajouterCommodite(int idCommodite, String description, int surplusprix)
            throws SQLException, IFT287Exception, Exception
    {
        try
        {
            // Vérifie si le client existe déja
            if (commodite.existe(idCommodite))
                throw new IFT287Exception("Commodite existe déjà: " + idCommodite);
            // Ajout du client dans la table des clients
            commodite.ajouterCommodite(idCommodite, description, surplusprix);

            // Commit
            cx.commit();
        }
        catch (Exception e)
        {
            cx.rollback();
            throw e;
        }
    }

    /**
     * Suppression d'un client
     */
    public int supprimerCommodite(int idcommodite) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            //Vérification de l'existence du client
            if (!commodite.existe(idcommodite)) {
                throw new IFT287Exception("La commodite n'existe pas: " + idcommodite);
            }

            //Suppression du client
            int resultatSuppression = commodite.supprimerCommodite(idcommodite);

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
