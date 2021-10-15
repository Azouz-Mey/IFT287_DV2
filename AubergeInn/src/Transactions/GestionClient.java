package Transactions;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import Tables.TableClient;
import Tuples.TupleClient;

import java.sql.SQLException;

/**
 * Gestion des transactions de reliées à la création et suppresion de clients
 */

public class GestionClient {

    private TableClient client;
    private Connexion cx;

    /**
     * Creation d'une instance
     */
    public GestionClient(TableClient client) throws IFT287Exception
    {
        this.cx = client.getConnexion();
       // if (client.getConnexion() != reservation.getConnexion())
           // throw new BiblioException("Les instances de livre et de reservation n'utilisent pas la même connexion au serveur");
        this.client = client;
        //this.reservation = reservation;
    }

    /**
     * Ajout d'un nouveau client dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void ajouterClient(int idclient, String nom, String prenom, int age)
            throws SQLException, IFT287Exception, Exception
    {
        try
        {
            // Vérifie si le client existe déja
            if (client.existe(idclient))
               throw new IFT287Exception("Client existe déjà: " + idclient);
            // Ajout du client dans la table des clients
            client.ajouterClient(idclient, nom, prenom, age);

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
    public int supprimerClient(int idclient) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            //Vérification de l'existence du client
            if (!client.existe(idclient)) {
                throw new IFT287Exception("Le client n'existe pas: " + idclient);
            }

            //Suppression du client
            int resultatSuppression = client.supprimerClient(idclient);

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
