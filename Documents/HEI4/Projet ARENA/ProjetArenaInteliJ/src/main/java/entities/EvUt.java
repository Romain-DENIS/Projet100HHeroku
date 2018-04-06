package entities;

public class EvUt {

    private int id;
    private String pseudo;
    private boolean paye;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public boolean isPaye() {
        return paye;
    }

    public void setPaye(boolean paye) {
        this.paye = paye;
    }

    public EvUt(int id, String pseudo, boolean paye) {
        this.id = id;
        this.pseudo = pseudo;
        this.paye = paye;
    }
}
