package dao.impl;

import dao.CommentaireDao;
import entities.Commentaire;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaireDaoImpl implements CommentaireDao {

    @Override
    public List<Commentaire> listeCommentaires() {
        //lister les commentaires par pseudo
        String query = "SELECT * FROM commentaire ORDER BY pseudo;";
        List<Commentaire> listOfCommentaires = new ArrayList<>();
        try (
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                listOfCommentaires.add(new Commentaire( resultSet.getInt("idevut"),resultSet.getString("pseudo"), resultSet.getString("commentaire")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfCommentaires;
    }

    @Override
    public void deleteCommentaire(String pseudo, int idevut) {
        //supprimer un commentaire
        String query = "DELETE FROM commentaire WHERE pseudo=? AND idevut=?";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, pseudo);
            stmt.setInt(2, idevut);
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Commentaire getCommentaire(String pseudo, int idevut) {
        //obtenir un commentaire à l'aide d'un pseudo et de l'id de l'evenement
        String query = "SELECT * FROM commentaire WHERE pseudo=? AND idevut=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, pseudo);
            statement.setInt(2, idevut);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Commentaire(resultSet.getInt("idevut"),resultSet.getString("pseudo"), resultSet.getString("commentaire"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Commentaire addCommentaire(Commentaire commentaire){
        //ajouter un commentaire
        String query ="INSERT INTO commentaire(idevut,pseudo,commentaire) VALUES(?,?,?)";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,commentaire.getId());
            stmt.setString(2,commentaire.getPseudo());
            stmt.setString(3,commentaire.getCommentaire());
            stmt.executeUpdate();
            return commentaire;

        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }

}
