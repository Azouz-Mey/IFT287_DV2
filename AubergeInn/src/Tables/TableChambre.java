package Tables;

import AubergeInn.Connexion;
import Tuples.TupleChambre;
import Tuples.TupleCommodite;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableChambre {

    private final PreparedStatement stmtInsert; /** Ajouter une chambre à la bdd */
    private final PreparedStatement stmtExiste; /** Vérifier si une chambre existe deja dans la bdd */
    private final PreparedStatement stmtDelete; /** Supprimer une chambre de la bdd */
    private final PreparedStatement stmtDisplay; /**Afficher les chambres libres*/
    private final PreparedStatement stmtPriceLocation;
    private final Connexion cx;

    /**
     * Creation d'une instance.
     */
    public TableChambre(Connexion cx) throws SQLException
    {
        this.cx = cx;
        stmtExiste = cx.getConnection().prepareStatement(
                "select idchambre, nom, typelit, prixbase from chambre where idchambre = ?");
        stmtInsert = cx.getConnection().prepareStatement("insert into chambre (idchambre, nom, typelit, prixbase) " + "values (?,?,?,?)");
        stmtDelete = cx.getConnection().prepareStatement("delete from chambre where idchambre = ?");
       // stmtDisplay = cx.getConnection().prepareStatement("select idchambre, nom, typelit, prixbase from chambre C inner join reservation R on C.idchambre = R.idchambre");
        stmtPriceLocation = cx.getConnection().prepareStatement("select C.prixbase + SUM(Com.prix) as prix from chambre C \n" +
                "join chambrecommodite CC on C.idchambre = CC.idchambre \n" +
                "join commodite Com on  Com.idcommodite = CC.idcommodite =\n" +
                "where C.idchambre = ?\n");

        stmtDisplay = cx.getConnection().prepareStatement("select C.idChambre, C.nom,C.typelit, C.prixbase from chambre C\n" +
                "left join reservation R on C.idChambre = R.idChambre \n" +
                "where R.datedebut < ? and R.datefin > ?" +
                "EXCEPT\n" +
                "select C.idChambre, C.nom, C.prixbase, C.typelit from chambre C \n" +
                "inner join reservation R on C.idChambre = R.idChambre       \n" +
                "where R.datedebut < ? and R.datefin > ?");
    }

    /**
     * Retourner la connexion associée.
     */
    public Connexion getConnexion()
    {
        return cx;
    }

    /**
     * Vérifie si une chambre existe.
     */
    public boolean existe(int idchambre) throws SQLException
    {
        stmtExiste.setInt(1, idchambre);
        ResultSet rset = stmtExiste.executeQuery();
        boolean chambreExiste = rset.next();
        rset.close();
        return chambreExiste;
    }

    /**
     * Ajout d'une nouvelle chambre dans la base de données.
     */
    public void ajouterChambre(int idchambre, String nom, String typelit, int prixbase) throws SQLException
    {
        /* Ajout d'une chambre */
        stmtInsert.setInt(1, idchambre);
        stmtInsert.setString(2, nom);
        stmtInsert.setString(3, typelit);
        stmtInsert.setInt(4, prixbase);
        stmtInsert.executeUpdate();
    }
    /**
     * Suppression d'une chambre
     */
    public int supprimerClient(int idchambre) throws SQLException
    {
        /* Suppression d'une chambre */
        stmtDelete.setInt(1, idchambre);
        return stmtDelete.executeUpdate();
    }
    /**
     * Ajout d'une nouvelle chambre dans la base de données.
     */
    public List<TupleChambre> afficherChambresLibres(Date dateDebut, Date dateFin) throws SQLException {
        stmtDisplay.setDate(1, dateDebut);
        stmtDisplay.setDate(2, dateFin);
        stmtDisplay.setDate(3, dateDebut);
        stmtDisplay.setDate(4, dateFin);

        ResultSet resultSet = stmtDisplay.executeQuery();
        List<TupleChambre> resultat = new ArrayList<>();
        while (resultSet.next()) {
            resultat.add(new TupleChambre(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)));
        }
        return resultat;
    }
    public static void afficherChambre(TupleChambre chambre) {
        System.out.println("\tChambre " + chambre.getIdChambre());
        System.out.println("\t\tNom: " + chambre.getNom());
        System.out.println("\t\tLit: " + chambre.getTypelit());
        System.out.println("\t\tPrix de base:  " + chambre.getPrixbase());
        //System.out.println("\t\tPrix total:  " + chambre.getPrixAvecCommodite()));
        System.out.println("\t\tCommodité compris: ");
       /* for (TupleCommodite commodite : chambre.getCommoditeList()) {
            afficherCommodite(commodite);
        }*/
    }
}
