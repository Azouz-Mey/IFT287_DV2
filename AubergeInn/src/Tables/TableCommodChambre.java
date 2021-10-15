package Tables;

import AubergeInn.Connexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableCommodChambre {

    private final PreparedStatement stmtExist;
    private final PreparedStatement stmtInclude; /** Ajouter une commodite à une chambre */
    private final PreparedStatement stmtRemove; /** Supprimer une commodite d'une chambre */

    private final Connexion cx;

    /**
     * Creation d'une instance.
     */
    public TableCommodChambre(Connexion cx) throws SQLException
    {
        this.cx = cx;

        stmtExist = cx.getConnection().prepareStatement(
                "select idcommodite, idchambre from commodchambre where idcommodite = ? AND idchambre = ?");
        stmtInclude = cx.getConnection().prepareStatement("insert into commodchambre (idcommodite, idchambre)" + "values (?,?)");
        stmtRemove = cx.getConnection().prepareStatement("delete from commodchambre where idcommodite = ? AND idchambre = ?");

    }
    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si une commodite existe dans une chambre
     */
    public boolean existe(int idcommodite, int idchambre) throws SQLException
    {
        stmtExist.setInt(1, idcommodite);
        stmtExist.setInt(2, idchambre);
        ResultSet rset = stmtExist.executeQuery();
        boolean commodChambreExiste = rset.next();
        rset.close();
        return commodChambreExiste;
    }

    /**
     * Ajout d'une commodite à une chambre.
     */
    public void inclureCommodite(int idcommodite, int idchambre) throws SQLException
    {
        /* Ajout d'une commodite */
        stmtInclude.setInt(1, idcommodite);
        stmtInclude.setInt(2, idchambre);
        stmtInclude.executeUpdate();
    }

    /**
     * Suppression d'une commodite d'une chambre.
     */
    public int enleverCommodite(int idcommodite, int idchambre) throws SQLException
    {
        /* Suppression d'une commodite */
        stmtRemove.setInt(1, idcommodite);
        stmtRemove.setInt(2, idchambre);
        return stmtRemove.executeUpdate();
    }
}

