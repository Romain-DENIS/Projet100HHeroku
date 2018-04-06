package dao.impl;

import dao.EvenementDao;
import entities.Evenement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EvenementDaoImpl implements EvenementDao {

    @Override
    public List<Evenement> listeEvenement(){
        //lister les evenementpar id
        String query="SELECT * FROM evenement ORDER BY id;";
        List<Evenement> listOfEvenement= new ArrayList<>();
        try(
                Connection connection=DataSourceProvider.getDataSource().getConnection();
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query)
        ){
            while(resultSet.next()) {
                listOfEvenement.add(new Evenement(resultSet.getString("nomE"), resultSet.getString("descri"), resultSet.getDate("dateE").toLocalDate(), resultSet.getString("plateforme"),resultSet.getBoolean("interhei"),resultSet.getBoolean("payant")));

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listOfEvenement;
    }



    @Override
    public Evenement getEvenement(String nomE){
        //obtenir un evenement avec son nomE
        String query = "SELECT * FROM evenement WHERE nomE=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nomE);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Evenement(resultSet.getString("nomE"), resultSet.getString("descri"), resultSet.getDate("dateE").toLocalDate(), resultSet.getString("plateforme"),resultSet.getBoolean("interhei"),resultSet.getBoolean("payant"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getId(String nomE, LocalDate dateE){
        //obtenir l'id d'un evenement avec son nom et sa date(util pour les inscriptions)
        String query = "SELECT id FROM evenement WHERE nomE=? AND dateE=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nomE);
            statement.setDate(2,Date.valueOf(dateE));

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new String(resultSet.getString("id"));

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    @Override
    public Evenement addEvenement(Evenement evenement){
        //ajouter un evenement
        String query ="INSERT INTO evenement(nomE,descri,dateE,plateforme,interhei,payant) VALUES(?,?,?,?,?,?)";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,evenement.getNomE());
            stmt.setString(2,evenement.getDescri());
            stmt.setDate(3, Date.valueOf(evenement.getDate()));
            stmt.setString(4,evenement.getPlateforme());
            stmt.setBoolean(5,evenement.isInterhei());
            stmt.setBoolean(6,evenement.isPayant());
            stmt.executeUpdate();
            return evenement;

        } catch (SQLException e) {
            e.printStackTrace();
        }return null;
    }


    @Override
    public void deleteEvenement(String nomE, LocalDate dateE) {
        //supprimer un evenement
        String query ="DELETE FROM evenement WHERE nomE=? AND dateE=?";
        try {
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1,nomE);
            stmt.setDate(2,Date.valueOf(dateE));
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
