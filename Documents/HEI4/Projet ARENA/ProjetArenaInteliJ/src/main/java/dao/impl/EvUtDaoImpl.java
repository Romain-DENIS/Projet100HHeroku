package dao.impl;

import dao.EvUtDao;
import dao.EvenementDao;
import entities.EvUt;
import entities.Evenement;
import entities.Utilisateur;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EvUtDaoImpl implements EvUtDao{

    public EvUtDaoImpl(){
        this.addEvUt(new EvUt(1,"pseudo1",true));
        this.addEvUt(new EvUt(2,"pseudo2",false));

    }



        @Override
    public List<EvUt> listeEvUt(int id){
        //lister les utilisateurs participants a un evenement en fonction de l'id de l'evenement
        String query="SELECT * FROM evut WHERE id=?";
        List<EvUt> listOfEvUt= new ArrayList<>();
        try(
                Connection connection=DataSourceProvider.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try(ResultSet resultSet=statement.executeQuery())
            {
                while(resultSet.next()) {
                    listOfEvUt.add(new EvUt(resultSet.getInt("id"),resultSet.getString("pseudo"),resultSet.getBoolean("paye")));

                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listOfEvUt;
    }

    @Override
    public List<Evenement> listeEvUtEven(String pseudo){
        //lister les utilisateurs participants a un evenement en fonction de l'id de l'evenement
        String query="SELECT * FROM (evenement INNER JOIN evut ON evenement.id=evut.id) WHERE evut.pseudo=?";
        List<Evenement> listOfEven= new ArrayList<>();
        try(
                Connection connection=DataSourceProvider.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1,pseudo);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    listOfEven.add(new Evenement(resultSet.getInt("id"),resultSet.getString("nomE"), resultSet.getString("descri"), resultSet.getDate("dateE").toLocalDate(), resultSet.getString("plateforme"),resultSet.getBoolean("interhei"),resultSet.getInt("payant")));
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfEven;
    }

    @Override
    public List<Utilisateur> listeEvUtMail(int id){
        //lister les utilisateurs participants a un evenement en fonction de l'id de l'evenement
        String query="SELECT * FROM (utilisateur INNER JOIN evut ON utilisateur.pseudo=evut.pseudo) WHERE evut.id=?";
        List<Utilisateur> listOfUtilisateurP= new ArrayList<>();
        try(
                Connection connection=DataSourceProvider.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
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
    public Integer listeEvUtPrix(int id){
        //lister les utilisateurs participants a un evenement en fonction de l'id de l'evenement
        String query="SELECT evenement.payant FROM (evut INNER JOIN evenement ON evenement.id=evut.id) WHERE evut.id=? AND evut.paye=1";
        Integer listOfEvUt=0;
        try(
                Connection connection=DataSourceProvider.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try(ResultSet resultSet=statement.executeQuery())
            {
                while(resultSet.next()) {
                    listOfEvUt=listOfEvUt+resultSet.getInt("payant");

                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return listOfEvUt;
    }




        @Override
        public EvUt getEvUt(String pseudo, int id){
            //obtenir une inscription a un evenement a l'aide du pseudo et de l'id (utile pour verifier le payment)
            String query = "SELECT * FROM evut WHERE pseudo=? AND id=?";
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, pseudo);
                statement.setInt(2, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new EvUt(resultSet.getInt("id"),resultSet.getString("pseudo"),resultSet.getBoolean("paye"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        public EvUt addEvUt(EvUt evut){
            //s'inscrire à un evenemùent en tant qu'utilisateur
            String query ="INSERT INTO evut(id,pseudo,paye) VALUES(?,?,?)";
            try {
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1,evut.getId());
                stmt.setString(2,evut.getPseudo());
                stmt.setBoolean(3,evut.isPaye());
                stmt.executeUpdate();
                return evut;

            } catch (SQLException e) {
                e.printStackTrace();
            }return null;
        }


        @Override
        public void deleteEvUt(String pseudo, int id){
            //supprimer une inscription
            String query ="DELETE FROM evut WHERE pseudo=? AND id=?";
            try {
                Connection connection = DataSourceProvider.getDataSource().getConnection();
                PreparedStatement stmt = connection.prepareStatement(query);

                stmt.setString(1,pseudo);
                stmt.setInt(2,id);
                stmt.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


