package Transactions;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import Tables.TableChambre;
import Tables.TableClient;
import Tuples.TupleChambre;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class GestionChambre {

    private TableChambre chambre;
    private Connexion cx;

    /**
     * Creation d'une instance
     */
    public GestionChambre(TableChambre chambre) throws IFT287Exception
    {
        /** A ajouter : commodité */
        this.cx = chambre.getConnexion();
        this.chambre = chambre;
    }

    /**
     * Ajout d'une nouvelle chambre dans la base de données. S'il existe déjà, une
     * exception est levée.
     */
    public void ajouterChambre(int idchambre, String nom, String typelit, int prixbase)
            throws SQLException, IFT287Exception, Exception
    {
        try
        {
            // Vérifie si la chambre existe déja
            if (chambre.existe(idchambre))
                throw new IFT287Exception("Chambre existe déjà: " + idchambre);
            // Ajout de la chambre dans la table des chambres
            chambre.ajouterChambre(idchambre, nom, typelit, prixbase);
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
    public int supprimerChambre(int idchambre) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            //Vérification de l'existence du client
            /** A ajouter verif s'il elle est réservée ou pas */
            if (!chambre.existe(idchambre)) {
                throw new IFT287Exception("La chambre n'existe pas: " + idchambre);
            }
            //Suppression de la chambre
            int resultatSuppression = chambre.supprimerClient(idchambre);

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

    public TableChambre getChambre() {
        return chambre;
    }

    public void setChambre(TableChambre chambre) {
        this.chambre = chambre;
    }

    /**
     * Affichage des chambres libres
     */
    public List<TupleChambre> afficherChambresLibres(Date dateDebut, Date dateFin)
            throws SQLException, IFT287Exception, Exception
    { try {
        List<TupleChambre> chambresLibres = chambre.afficherChambresLibres(dateDebut, dateFin);
       /* for (TupleChambre chambre : chambresLibres) {
            chambre.setCommoditeList(commodite.obtenirListeCommodite(chambre.getIdChambre()));
        }*/

        return chambresLibres;
    }
    catch (Exception e) {
        cx.rollback();
        throw e;
        }
    }
}
