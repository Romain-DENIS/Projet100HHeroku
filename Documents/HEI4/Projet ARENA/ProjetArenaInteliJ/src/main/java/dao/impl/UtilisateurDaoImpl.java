package dao.impl;

import dao.UtilisateurDao;
import entities.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDaoImpl implements UtilisateurDao{

    @Override
    public List<Utilisateur> listeUtilisateur(){
        //lister les utilisateur par leur pseudo
        String query="SELECT * FROM utilisateur ORDER BY pseudo;";
        List<Utilisateur> listOfUtilisateur= new ArrayList<>();
        try(
                Connection connection=DataSourceProvider.getDataSource().getConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query)
        ){
            while(resultSet.next()) {
                listOfUtilisateur.add(new Utilisateur(resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("pseudo"), resultSet.getString("mot_de_passe"), resultSet.getString("email"), resultSet.getString("classe"),resultSet.getBoolean("notif")));

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listOfUtilisateur;
    }

    @Override
    public List<Utilisateur> listeUtilisateurPlateforme(String Plat){
        //lister les utilisateur par leur pseudo
        String query="SELECT * FROM (utilisateur INNER JOIN (evut INNER JOIN evenement on evut.id=evenement.id) on evut.pseudo=utilisateur.pseudo ) WHERE evenement.plateforme=?";
        List<Utilisateur> listOfUtilisateurP= new ArrayList<>();
        try(
                Connection connection=DataSourceProvider.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, Plat);
                try(ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()) {
                        listOfUtilisateurP.add(new Utilisateur(resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("pseudo"), resultSet.getString("mot_de_passe"), resultSet.getString("email"), resultSet.getString("classe"),resultSet.getBoolean("notif")));
                        }
                }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfUtilisateurP;
    }

    @Override
    public Utilisateur getUtilisateur(String pseudo){
        //recuperer les info d'un utilisateur avec juste son pseudo
        String query = "SELECT * FROM utilisateur WHERE pseudo=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pseudo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Utilisateur(resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("pseudo"), resultSet.getString("mot_de_passe"), resultSet.getString("email"), resultSet.getString("classe"),resultSet.getBoolean("notif"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMotDePasse(String pseudo){
        //obtenir le mot de passe d'un utilisateur avec son pseudo
        String query = "SELECT mot_de_passe FROM utilisateur WHERE pseudo=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, pseudo);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new String(resultSet.getString("mot_de_passe"));

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    @Override
    public Utilisateur addUtilisateur(Utilisateur utilisateur){
        //ajouter un utilisateur
        String query ="INSERT INTO utilisateur(nom,prenom,pseudo,mot_de_passe,email,classe,notif) VALUES(?,?,?,?,?,?,?)";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,utilisateur.getNom());
            stmt.setString(2,utilisateur.getPrenom());
            stmt.setString(3,utilisateur.getPseudo());
            stmt.setString(4,utilisateur.getMot_de_passe());
            stmt.setString(5,utilisateur.getEmail());
            stmt.setString(6,utilisateur.getClasse());
            stmt.setBoolean(7,utilisateur.getNotif());
            stmt.executeUpdate();
            return utilisateur;

        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }

    @Override
    public void deleteUtilisateur(String pseudo) {
        //supprimer un utilisateur à l'aide de son pseudo
        String query ="DELETE FROM utilisateur WHERE pseudo=?";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1,pseudo);

            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
