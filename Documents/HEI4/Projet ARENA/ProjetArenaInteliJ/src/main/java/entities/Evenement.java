package entities;

import java.time.LocalDate;

public class Evenement {

    private static int id;
    private String nomE;
    private String descri;
    private LocalDate dateE;
    private String plateforme;
    private boolean interhei;
    private int payant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomE() {
        return nomE;
    }

    public void setNomE(String nomE) {
        this.nomE = nomE;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public LocalDate getDate() {
        return dateE;
    }

    public void setDate(LocalDate dateE) {
        this.dateE = dateE;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public boolean isInterhei() {
        return interhei;
    }

    public void setInterhei(boolean interhei) {
        this.interhei = interhei;
    }

    public int isPayant() {
        return payant;
    }

    public void setPayant(int payant) {
        this.payant = payant;
    }

    public Evenement( String nomE, String descri, LocalDate dateE, String plateforme, boolean interhei, int payant) {

        this.nomE = nomE;
        this.descri = descri;
        this.dateE = dateE;
        this.plateforme = plateforme;
        this.interhei = interhei;
        this.payant = payant;
    }
}
