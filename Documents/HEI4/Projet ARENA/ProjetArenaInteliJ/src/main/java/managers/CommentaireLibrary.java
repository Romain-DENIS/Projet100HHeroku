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
    public List<Commentaire> listeCommentaire(){ return commentaireDao.listeCommentaires();}
}
