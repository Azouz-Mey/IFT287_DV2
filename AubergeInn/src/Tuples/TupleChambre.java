package Tuples;

import java.util.ArrayList;
import java.util.List;

public class TupleChambre {


    private int idChambre;
    private String nom;
    private String typelit;
    private int prixbase;
    private List<TupleCommodite> commoditeList;


    public TupleChambre(int idChambre, String nom, String typelit, int prixbase) {
        this.nom = nom;
        this.idChambre = idChambre;
        this.typelit = typelit;
        this.prixbase = prixbase;
        commoditeList = new ArrayList<>();

    }

    public int getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(int idChambre) {
        this.idChambre = idChambre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypelit() {
        return typelit;
    }

    public void setTypelit(String typelit) {
        this.typelit = typelit;
    }

    public int getPrixbase() {
        return prixbase;
    }

    public void setPrixbase(int prixbase) {
        this.prixbase = prixbase;
    }

    public void setCommoditeList(List<TupleCommodite> commoditeList) {
        this.commoditeList = commoditeList;
    }
    public List<TupleCommodite> getCommoditeList() {
        return this.commoditeList;
    }
}
