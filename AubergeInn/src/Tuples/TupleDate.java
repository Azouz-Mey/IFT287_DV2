package Tuples;

import java.sql.Date;

public class TupleDate {
    private Date datedebut;
    private Date datefin;

    public TupleDate(Date datedebut, Date datefin) {
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
}
