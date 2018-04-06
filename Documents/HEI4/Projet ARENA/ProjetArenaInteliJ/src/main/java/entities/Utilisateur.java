package entities;



public class Utilisateur {

    private String nom;
    private String prenom;
    private String pseudo;
    private String mot_de_passe;
    private String email;
    private String classe;
    private Boolean notif;



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Boolean getNotif() {
        return notif;
    }

    public void setNotif(Boolean notif) {
        this.notif = notif;
    }

    public Utilisateur(String nom, String prenom, String pseudo, String mot_de_passe, String email, String classe, Boolean notif) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.classe = classe;
        this.notif = notif;
    }
}
