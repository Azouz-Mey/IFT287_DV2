package Tables;

import AubergeInn.Connexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableReservation {

    private final PreparedStatement stmtInsert; /** Ajouter une reservation à la bdd */
    private final PreparedStatement stmtExist; /** Vérifier si une reservation existe deja dans la bdd */
    private final PreparedStatement stmtDelete; /** Supprimer une reservation de la bdd */

    private final Connexion cx;

    /**
     * Creation d'une instance.
     */
    public TableReservation(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtExist = cx.getConnection().prepareStatement(
                "select idClient, idchambre, datedebut, datefin from reservation where idchambre = ? AND (datedebut <= ? AND datefin >= ?)"); //verifchmbrreserv
        stmtInsert = cx.getConnection().prepareStatement("insert into reservation (idclient, idchambre, datedebut, datefin) " + "values (?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from reservation where idclient = ? AND idchambre = ?");
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si une chambre est deja reservee ou non
     */
    public boolean chambreReservee(int idchambre, Date datedebut, Date datefin) throws SQLException
    {
        stmtExist.setInt(1, idchambre);
        stmtExist.setDate(2, datedebut);
        stmtExist.setDate(3, datefin);

        ResultSet rset = stmtExist.executeQuery();
        boolean reservationChambreExiste = rset.next();
        rset.close();
        return reservationChambreExiste;
    }

    /**
     * Reserver une chambre pour un client
     */
    public void reserver(int idclient, int idchambre, Date datedebut, Date datefin) throws SQLException {
        stmtInsert.setInt(1, idclient);
        stmtInsert.setInt(2, idchambre);
        stmtInsert.setDate(3, datedebut);
        stmtInsert.setDate(4, datefin);
        stmtInsert.executeUpdate();
    }

}
