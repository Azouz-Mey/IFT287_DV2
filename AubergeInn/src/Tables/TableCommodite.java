package Tables;

import AubergeInn.Connexion;
import Tuples.TupleCommodite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableCommodite {

    private final PreparedStatement stmtInsert; /** Ajouter une commodite à la bdd */
    private final PreparedStatement stmtExiste; /** Vérifier si une commodite existe deja dans la bdd */
    private final PreparedStatement stmtDelete; /** Supprimer une commodite */
   // private final PreparedStatement stmtListCommoditeParChambre;
    private final PreparedStatement stmtInclure; /** Ajouter une commodite à une chambre*/

    private final Connexion cx;

    /**
     * Creation d'une instance.
     */
    public TableCommodite(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement(
                "select idcommodite, description, surplusprix from commodite where idcommodite = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into commodite (idcommodite, description, surplusprix) " + "values (?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from commodite where idcommodite = ?");
        /*stmtCommoditeChambreListe = cx.getConnection().prepareStatement("select Orders.OrderID, Customers.CustomerName, Orders.OrderDate
                FROM Orders
                INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID;)*/
        stmtInclure = cx.getConnection().prepareStatement("insert into commodchambre (idcommodite, idchambre)" + "values(?,?,?)");

    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si une commodite existe.
     */
    public boolean existe(int idcommodite) throws SQLException
    {
        stmtExiste.setInt(1, idcommodite);
        ResultSet rset = stmtExiste.executeQuery();
        boolean commoditeExiste = rset.next();
        rset.close();
        return commoditeExiste;
    }
    /**
     * Ajout d'un nouveau client dans la base de données.
     */
    public void ajouterCommodite(int idcommodite, String description, int surplusprix) throws SQLException
    {
        /* Ajout d'une commodite */
        stmtInsert.setInt(1, idcommodite);
        stmtInsert.setString(2, description);
        stmtInsert.setInt(3, surplusprix);
        stmtInsert.executeUpdate();
    }

    /**
     * Suppression d'une commodite
     */
    public int supprimerCommodite(int idcommodite) throws SQLException
    {
        /* Suppression d'une commodite */
        stmtDelete.setInt(1, idcommodite);
        return stmtDelete.executeUpdate();
    }

    public static void afficherCommodite(TupleCommodite commodite) {
        System.out.println("\t\t\tCommodite " + commodite.getIdcommodite());
        System.out.println("\t\t\t\tDescription: " + commodite.getDescription());
        System.out.println("\t\t\t\tPrix: " + commodite.getSurplusprix());
    }
}
