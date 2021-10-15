// Travail fait par :
//   NomEquipier1 - Matricule
//   NomEquipier2 - Matricule

package AubergeInn;

import Tables.TableChambre;
import Tuples.TupleChambre;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.sql.*;

/**
 * Fichier de base pour le TP2 du cours IFT287
 *
 * <pre>
 * 
 * Vincent Ducharme
 * Universite de Sherbrooke
 * Version 1.0 - 7 juillet 2016
 * IFT287 - Exploitation de BD relationnelles et OO
 * 
 * Ce programme permet d'appeler des transactions d'un systeme
 * de gestion utilisant une base de donnees.
 *
 * Paramètres du programme
 * 0- site du serveur SQL ("local" ou "dinf")
 * 1- nom de la BD
 * 2- user id pour etablir une connexion avec le serveur SQL
 * 3- mot de passe pour le user id
 * 4- fichier de transaction [optionnel]
 *           si non spécifié, les transactions sont lues au
 *           clavier (System.in)
 *
 * Pré-condition
 *   - La base de donnees doit exister
 *
 * Post-condition
 *   - Le programme effectue les mises à jour associees à chaque
 *     transaction
 * </pre>
 */
public class AubergeInn
{
    private static Connexion cx;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        if (args.length < 4)
        {
            System.out.println("Usage: java AubergeInn.AubergeInn <serveur> <bd> <user> <password> [<fichier-transactions>]");
            return;
        }
        
        cx = null;
        
        try
        {
            // Il est possible que vous ayez à déplacer la connexion ailleurs.
            // N'hésitez pas à le faire!
            cx = new Connexion(args[0], args[1], args[2], args[3]);
            BufferedReader reader = ouvrirFichier(args);
            String transaction = lireTransaction(reader);
            while (!finTransaction(transaction))
            {
                executerTransaction(transaction);
                transaction = lireTransaction(reader);
            }
        }
        finally
        {
            if (cx != null)
                cx.fermer();
        }
    }

    /**
     * Decodage et traitement d'une transaction
     */
    static void executerTransaction(String transaction) throws Exception, IFT287Exception
    {
        try
        {
            System.out.print(transaction);
            // Decoupage de la transaction en mots
            StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
            if (tokenizer.hasMoreTokens())
            {
                String command = tokenizer.nextToken();
                // Vous devez remplacer la chaine "commande1" et "commande2" par
                // les commandes de votre programme. Vous pouvez ajouter autant
                // de else if que necessaire. Vous n'avez pas a traiter la
                // commande "quitter".
                if (command.equals("ajouterClient"))
                {
                    // Lecture des parametres
                    int idclient = readInt(tokenizer);
                    String nom = readString(tokenizer);
                    String prenom = readString(tokenizer);
                    int age = readInt(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionAubergeInn.getGestionClient().ajouterClient(idclient, nom, prenom, age);
                }
                else if (command.equals("supprimerClient"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    int idclient = readInt(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    gestionAubergeInn.getGestionClient().supprimerClient(idclient);
                }
                else if (command.equals("ajouterChambre"))
                {
                    // Lecture des parametres
                    int idchambre = readInt(tokenizer);
                    String nom = readString(tokenizer);
                    String typelit = readString(tokenizer);
                    int prixbase = readInt(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionAubergeInn.getGestionChambre().ajouterChambre(idchambre, nom, typelit, prixbase);
                }
                else if (command.equals("supprimerChambre"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    int idchambre = readInt(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    gestionAubergeInn.getGestionChambre().supprimerChambre(idchambre);
                }
                else if (command.equals("ajouterCommodite"))
                {
                    // Lecture des parametres
                    int idcommodite = readInt(tokenizer);
                    String description = readString(tokenizer);
                    int surpluxprix = readInt(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionAubergeInn.getGestionCommodite().ajouterCommodite(idcommodite, description, surpluxprix);
                }
                else if (command.equals("supprimerCommodite"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    int idcommodite = readInt(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    gestionAubergeInn.getGestionCommodite().supprimerCommodite(idcommodite);
                }
                else if (command.equals("inclureCommodite"))
                {
                    // Lecture des parametres
                    int idcommodite = readInt(tokenizer);
                    int idchambre = readInt(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionAubergeInn.getGestionCommodChambre().inclureCommodite(idcommodite, idchambre);
                }
                else if (command.equals("enleverCommodite"))
                {
                    // Lire les parametres ici et appeler la bonne methode
                    int idcommodite = readInt(tokenizer);
                    int idchambre = readInt(tokenizer);

                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    gestionAubergeInn.getGestionCommodChambre().enleverCommodite(idcommodite, idchambre);
                }
                else if (command.equals("ajouterDate"))
                {
                    // Lecture des parametres
                    Date datedebut = readDate(tokenizer);
                    Date datefin = readDate(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionAubergeInn.getGestionDate().ajouterDate(datedebut, datefin);
                }
                else if (command.equals("reserver"))
                {
                    // Lecture des parametres
                    int idclient = readInt(tokenizer);
                    int idchambre = readInt(tokenizer);
                    Date datedebut = readDate(tokenizer);
                    Date datefin = readDate(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    gestionAubergeInn.getGestionReservation().reserver(idclient, idchambre, datedebut, datefin);
                }
                else if (command.equals("afficherChambresLibres"))
                {
                    // Lecture des parametres
                    Date datedebut = readDate(tokenizer);
                    Date datefin = readDate(tokenizer);
                    GestionAubergeInn gestionAubergeInn = new GestionAubergeInn("local","auberge_Inn", "postgres", "AZOUZ123@");
                    // Appel de la methode des gestionnaires qui traite la transaction specifique
                    List<TupleChambre> chambresLibres = gestionAubergeInn.getGestionChambre().afficherChambresLibres(datedebut, datefin);
                    for (TupleChambre chambre : chambresLibres) {
                        gestionAubergeInn.getGestionChambre().getChambre().afficherChambre(chambre);
                    }
                }

                else
                {
                    System.out.println(" : Transaction non reconnue");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(" " + e.toString());
            // Ce rollback est ici seulement pour vous aider et éviter des problèmes lors de la correction
            // automatique. En théorie, il ne sert à rien et ne devrait pas apparaître ici dans un programme
            // fini et fonctionnel sans bogues.
            cx.rollback();
        }
    }

    
    // ****************************************************************
    // *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
    // ****************************************************************

    /**
     * Ouvre le fichier de transaction, ou lit à partir de System.in
     */
    public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException
    {
        if (args.length < 5)
            // Lecture au clavier
            return new BufferedReader(new InputStreamReader(System.in));
        else
            // Lecture dans le fichier passe en parametre
            return new BufferedReader(new InputStreamReader(new FileInputStream(args[4])));
    }

    /**
     * Lecture d'une transaction
     */
    static String lireTransaction(BufferedReader reader) throws IOException
    {
        return reader.readLine();
    }

    /**
     * Verifie si la fin du traitement des transactions est atteinte.
     */
    static boolean finTransaction(String transaction)
    {
        // fin de fichier atteinte
        return (transaction == null || transaction.equals("quitter"));
    }

    /** Lecture d'une chaine de caracteres de la transaction entree a l'ecran */
    static String readString(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
            return tokenizer.nextToken();
        else
            throw new Exception("Autre parametre attendu");
    }

    /**
     * Lecture d'un int java de la transaction entree a l'ecran
     */
    static int readInt(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Integer.valueOf(token).intValue();
            }
            catch (NumberFormatException e)
            {
                throw new Exception("Nombre attendu a la place de \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

    static Date readDate(StringTokenizer tokenizer) throws Exception
    {
        if (tokenizer.hasMoreElements())
        {
            String token = tokenizer.nextToken();
            try
            {
                return Date.valueOf(token);
            }
            catch (IllegalArgumentException e)
            {
                throw new Exception("Date dans un format invalide - \"" + token + "\"");
            }
        }
        else
            throw new Exception("Autre parametre attendu");
    }

}
