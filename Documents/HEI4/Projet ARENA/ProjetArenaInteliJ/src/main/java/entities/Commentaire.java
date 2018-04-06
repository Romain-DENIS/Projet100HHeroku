package entities;

public class Commentaire {

    private int idevut;
    private String pseudo;
    private String commentaire;

    public Commentaire(int id, String pseudo, String commentaire) {
        this.idevut=id;
        this.pseudo = pseudo;
        this.commentaire = commentaire;
    }

    public int getId() {
        return idevut;
    }

    public void setId(int id) {
        this.idevut = id;
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
