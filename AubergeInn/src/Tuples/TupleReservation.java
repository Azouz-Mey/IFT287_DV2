package Tuples;

import java.util.Date;

public class TupleReservation {

    private int idclient;
    private int idchambre;
    private Date datedebut;
    private Date datefin;

    public TupleReservation()
    {
    }
    public TupleReservation(int idclient, int idchambre, Date datedebut, Date datefin) {
        this.idclient = idclient;
        this.idchambre = idchambre;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getIdchambre() {
        return idchambre;
    }

    public void setIdchambre(int idchambre) {
        this.idchambre = idchambre;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
}
