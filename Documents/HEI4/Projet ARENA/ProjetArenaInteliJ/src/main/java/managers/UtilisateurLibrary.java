package managers;

import dao.UtilisateurDao;
import dao.impl.UtilisateurDaoImpl;
import entities.Utilisateur;

import java.util.List;

public class UtilisateurLibrary {
    private static class UtilisateurLibraryHolder{
        private final static UtilisateurLibrary instance= new UtilisateurLibrary();
    }
    public static UtilisateurLibrary getInstance(){return UtilisateurLibraryHolder.instance;}



    private UtilisateurDao utilisateurDao=new UtilisateurDaoImpl();

    private UtilisateurLibrary(){}

    public List<Utilisateur> listeUtilisateur(){return utilisateurDao.listeUtilisateur();}

    public List<Utilisateur> listeUtilisateurPlateforme(String Plat){return utilisateurDao.listeUtilisateurPlateforme(Plat);}

    public Utilisateur getUtilisateur(String pseudo){return utilisateurDao.getUtilisateur(pseudo);}

    public String getMotDePasse(String pseudo){
        if (pseudo == null || "".equals(pseudo)) {

            throw new IllegalArgumentException("Le pseudo de l'utilisateur ne doit pas être vide.");

        }

        return utilisateurDao.getMotDePasse(pseudo);

    }

    public Utilisateur addUtilisateur(Utilisateur utilisateur){//utilisateurDao.addUtilisateur(utilisateur);
        return utilisateurDao.addUtilisateur(utilisateur);
    }

    public void deleteUtilisateur(String pseudo){utilisateurDao.deleteUtilisateur(pseudo);
    }
}
