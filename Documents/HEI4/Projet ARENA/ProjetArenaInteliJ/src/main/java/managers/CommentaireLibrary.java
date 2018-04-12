package managers;

import dao.CommentaireDao;
import dao.impl.CommentaireDaoImpl;
import entities.Commentaire;

import java.util.List;

public class CommentaireLibrary {

    private static class CommentaireLibraryHolder{
        private final static CommentaireLibrary instance= new CommentaireLibrary();
    }
    public static CommentaireLibrary getInstance(){return CommentaireLibraryHolder.instance;}
    private CommentaireDao commentaireDao = new CommentaireDaoImpl();
    private CommentaireLibrary(){};
    public Commentaire addCommentaire(Commentaire commentaire){return commentaireDao.addCommentaire(commentaire);}
    public Commentaire getCommentaire(String pseudo, int idevut){return commentaireDao.getCommentaire(pseudo, idevut);}
    public List<Commentaire> listeCommentaire(){ return commentaireDao.listeCommentaires();}
    public void deleteCommentaire(String pseudo, int idevut){commentaireDao.deleteCommentaire(pseudo, idevut);}
}
