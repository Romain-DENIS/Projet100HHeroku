package dao;

import entities.Commentaire;

import java.util.List;

public interface CommentaireDao {

    public List<Commentaire> listeCommentaires();

    public Commentaire getCommentaire(String pseudo, int idevut);


    public Commentaire addCommentaire(Commentaire commentaire);


    public void deleteCommentaire(String pseudo, int idevut);
}
