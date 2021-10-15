package Transactions;

import AubergeInn.Connexion;
import AubergeInn.IFT287Exception;
import Tables.TableChambre;
import Tables.TableClient;
import Tables.TableCommodChambre;
import Tables.TableCommodite;

import java.sql.SQLException;

public class GestionCommodChambre {

    private TableCommodChambre commodChambre;
    private TableCommodite commodite;
    private TableChambre chambre;

    private Connexion cx;

    /**
     * Creation d'une instance
     */
    public GestionCommodChambre(TableCommodChambre commodChambre, TableCommodite commodite, TableChambre chambre) throws IFT287Exception
    {
        this.cx = commodChambre.getConnexion();
        this.commodChambre = commodChambre;
        this.commodite = commodite;
        this.chambre = chambre;
    }

    /**
     *Ajout d'une commodite à une chambre. S'il existe déjà, une
     * exception est levée.
     */
    public void inclureCommodite(int idcommodite, int idchambre)
            throws SQLException, IFT287Exception, Exception
    {
        try
        {
            // Vérifie si le client existe déja
            if (commodChambre.existe(idcommodite, idchambre))
                throw new IFT287Exception("La commodite : "+ idcommodite + " existe déjà dans la chambre: " + idchambre);
            //Vérification de l'existence de la chambre
            if (!chambre.existe(idchambre)) {
                throw new IFT287Exception("Impossible d'inclure la commodité, la chambre n'extiste pas");
            }
            //Vérification de l'existence de la commmodite
            if (!commodite.existe(idcommodite)) {
                throw new IFT287Exception("Impossible d'inclure la commodité, la commodité n'extiste pas");
            }
            // Ajout de la commodite dans la chambre
            commodChambre.inclureCommodite(idcommodite, idchambre);

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
     * Suppression d'une commodite d'une chambre
     */
    public int enleverCommodite(int idcommodite, int idchambre) throws SQLException, IFT287Exception, Exception
    {
        try
        {
            if (!commodChambre.existe(idcommodite, idchambre)) {
                throw new IFT287Exception("L'association entre la chambre et la commodité n'existe pas: {idChambre: " + idchambre + " ,idCommodite: " + idcommodite + " }");
            }

            //Suppression de la commodite
            int resultatSuppression = commodChambre.enleverCommodite(idcommodite, idchambre);
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
