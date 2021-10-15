package Tuples;

public class TupleClient {

    private String prenom;
    private String nom;
    private int idClient;
    private int age;

    public TupleClient() {

    }

    public TupleClient(String nom, String prenom, int idClient, int age) {
        this.prenom = prenom;
        this.nom = nom;
        this.idClient = idClient;
        this.age = age;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return idClient;
    }

    public void setId(int id) {
        this.idClient = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
