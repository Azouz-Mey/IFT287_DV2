package Tables;

import AubergeInn.Connexion;
import Tuples.TupleClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Permet d'effectuer les accès à la table client.
 */
public class TableClient {

    private final PreparedStatement stmtInsert; /** Ajouter un client à la bdd */
    private final PreparedStatement stmtExiste; /** Vérifier si un client existe deja dans la bdd */
    private final PreparedStatement stmtDelete; /** Supprimer un client de la bdd */

    private final Connexion cx;

    /**
     * Creation d'une instance.
     */
    public TableClient(Connexion cx) throws SQLException
    {
            this.cx = cx;
            stmtExiste = cx.getConnection().prepareStatement(
                    "select idClient, prenom, nom, age from client where idClient = ?");
            stmtInsert = cx.getConnection().prepareStatement("insert into client (idclient, nom, prenom, age) " + "values (?,?,?,?)");
            stmtDelete = cx.getConnection().prepareStatement("delete from client where idclient = ?");
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si un client existe.
     */
    public boolean existe(int idclient) throws SQLException
    {
        stmtExiste.setInt(1, idclient);
        ResultSet rset = stmtExiste.executeQuery();
        boolean clientExiste = rset.next();
        rset.close();
        return clientExiste;
    }
    /**
     * Lecture d'un client.
     */
    /*public TupleClient getClient(int idclient) throws SQLException
    {
        stmtExiste.setInt(1, idclient);
        System.out.println("tsttt" + stmtExiste);

        ResultSet rset = stmtExiste.executeQuery();
        System.out.println("tsttt" + stmtExiste.executeQuery());
        if (rset.next())
        {
            TupleClient tupleClient = new TupleClient();
            tupleClient.setId(idclient);
            tupleClient.setNom(rset.getString(2));
            tupleClient.setPrenom(rset.getString(3));
            tupleClient.setAge(rset.getInt(4));
            rset.close();
            return tupleClient;
        }
        else
            return null;
    }
*/
    /**
     * Ajout d'un nouveau client dans la base de données.
     */
    public void ajouterClient(int idclient, String nom, String prenom, int age) throws SQLException
    {
        /* Ajout d'un client */
        stmtInsert.setInt(1, idclient);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, prenom);
        stmtInsert.setInt(4, age);
        stmtInsert.executeUpdate();
    }
    /**
     * Suppression d'un client.
     */
    public int supprimerClient(int idclient) throws SQLException
    {
        /* Suppression d'un client */
        stmtDelete.setInt(1, idclient);
        return stmtDelete.executeUpdate();
    }

}
