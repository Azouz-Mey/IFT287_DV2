package Tuples;

public class TupleCommodite {

    private int idcommodite;
    private int surplusprix;
    private String description;

    public TupleCommodite()
    {
    }
    public TupleCommodite(int idcommodite, int surplusprix, String description) {
        this.idcommodite = idcommodite;
        this.surplusprix = surplusprix;
        this.description = description;
    }

    public int getIdcommodite() {
        return idcommodite;
    }

    public void setIdcommodite(int idcommodite) {
        this.idcommodite = idcommodite;
    }

    public int getSurplusprix() {
        return surplusprix;
    }

    public void setSurplusprix(int surplusprix) {
        this.surplusprix = surplusprix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
