package dao;

import entities.Commentaire;

import java.util.List;

public interface CommentaireDao {

    public List<Commentaire> listeCommentaires();

    public Commentaire getCommentaire(String pseudo, int id);


    public Commentaire addCommentaire(Commentaire commentaire);


    public void deleteCommentaire(String pseudo, int id);
}
