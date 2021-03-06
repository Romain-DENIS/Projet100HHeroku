package dao.impl;

import dao.EvenementDao;
import entities.Evenement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EvenementDaoImpl implements EvenementDao {

    List<Evenement> evenementsT=new ArrayList<>();

    public EvenementDaoImpl(){
        this.addEvenement(new Evenement(null,"even1","descri1",LocalDate.of(2018,02,19),"ps4",true,1));
        this.addEvenement(new Evenement(null,"even2","descri2",LocalDate.of(2018,02,18),"ordi",false,1));
        this.addEvenement(new Evenement(null,"even3","descri3",LocalDate.of(2018,02,17),"xbox",true,0));
    }

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
                listOfEvenement.add(new Evenement(resultSet.getInt("id"),resultSet.getString("nomE"), resultSet.getString("descri"), resultSet.getDate("dateE").toLocalDate(), resultSet.getString("plateforme"),resultSet.getBoolean("interhei"),resultSet.getInt("payant")));

            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listOfEvenement;
    }



    @Override
    public Evenement getEvenement(int id){
        //obtenir un evenement avec son nomE
        String query = "SELECT * FROM evenement WHERE id=?";
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Evenement(resultSet.getInt("id"),resultSet.getString("nomE"), resultSet.getString("descri"), resultSet.getDate("dateE").toLocalDate(), resultSet.getString("plateforme"),resultSet.getBoolean("interhei"),resultSet.getInt("payant"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getId(String nomE, LocalDate dateE){
        //obtenir l'id d'un evenement avec son nom et sa date(util pour les inscriptions)
        String query = "SELECT id FROM evenement WHERE nomE=? AND dateE=?";

        try (Connection connection = DataSourceProvider.getDataSource().getConnection();

             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nomE);
            statement.setDate(2,Date.valueOf(dateE));

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return resultSet.getInt("id");

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

        String query ="INSERT INTO evenement(id,nomE,descri,dateE,plateforme,interhei,payant) VALUES(?,?,?,?,?,?,?)";
        try {

            evenementsT.add(evenement);
            Integer id=evenementsT.size();
            Connection connection = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.setString(2,evenement.getNomE());
            stmt.setString(3,evenement.getDescri());
            stmt.setDate(4, Date.valueOf(evenement.getDate()));
            stmt.setString(5,evenement.getPlateforme());
            stmt.setBoolean(6,evenement.isInterhei());
            stmt.setInt(7,evenement.isPayant());
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
