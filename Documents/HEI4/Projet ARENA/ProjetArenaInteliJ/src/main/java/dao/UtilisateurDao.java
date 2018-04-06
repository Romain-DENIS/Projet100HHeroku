package dao;

import entities.Utilisateur;

import java.util.List;

public interface UtilisateurDao {

    public List<Utilisateur> listeUtilisateur();

    public Utilisateur getUtilisateur(String pseudo);

    public String getMotDePasse(String mot_de_passe);

    public Utilisateur addUtilisateur(Utilisateur utilisateur);

    public void deleteUtilisateur(String pseudo);
}
