package Tuples;

import java.util.Date;

public class TupleCommodChambre {

    private int idcommodite;
    private int idchambre;

    public TupleCommodChambre()
    {
    }
    public TupleCommodChambre(int idcommodite, int idchambre) {
        this.idcommodite = idcommodite;
        this.idchambre = idchambre;
    }

    public int getIdcommodite() {
        return idcommodite;
    }

    public void setIdcommodite(int idcommodite) {
        this.idcommodite = idcommodite;
    }

    public int getIdchambre() {
        return idchambre;
    }

    public void setIdchambre(int idchambre) {
        this.idchambre = idchambre;
    }
}
