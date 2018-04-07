package entities;

public class Commentaire {

    private int idevut;
    private String pseudo;
    private String commentaire;

    public Commentaire(int idevut, String pseudo, String commentaire) {
        this.idevut=idevut;
        this.pseudo = pseudo;
        this.commentaire = commentaire;
    }

    public int getId() {
        return idevut;
    }

    public void setId(int idevut) {
        this.idevut = idevut;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
