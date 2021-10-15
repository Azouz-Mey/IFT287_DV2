package Tables;

import AubergeInn.Connexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableDate {
    private final PreparedStatement stmtInsert;
    /**
     * Ajouter un client à la bdd
     */
    private final PreparedStatement stmtExiste;
    /**
     * Vérifier si un client existe deja dans la bdd
     */
    private final PreparedStatement stmtDelete;
    /**
     * Supprimer un client de la bdd
     */

    private final Connexion cx;

    public TableDate(Connexion cx) throws SQLException {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement(
                "select * from Date where datedebut = ? and datefin = ?");
        stmtInsert = cx.getConnection().prepareStatement(
                "insert into Date (datedebut, datefin) " + "values (?,?)");
        stmtDelete = cx.getConnection().prepareStatement(
                "delete from Date where datedebut = ? and datefin = ?");

    }

    public void ajouterDate(Date datedebut, Date datefin) throws SQLException {
        stmtInsert.setDate(1, datedebut);
        stmtInsert.setDate(2, datefin);
        stmtInsert.executeUpdate();
    }

    public boolean existeDate(Date datedebut, Date datefin) throws SQLException {
        stmtExiste.setDate(1, datedebut);
        stmtExiste.setDate(2, datefin);
        ResultSet rset = stmtExiste.executeQuery();
        boolean DateExiste = rset.next();
        rset.close();
        return DateExiste;
    }

    public int SupprimerDate(Date datedebut, Date datefin) throws SQLException {
        stmtDelete.setDate(1,datedebut);
        stmtDelete.setDate(2,datefin);
        return stmtDelete.executeUpdate();
    }

    public Connexion getConnexion()
    {
        return cx;
    }
}
