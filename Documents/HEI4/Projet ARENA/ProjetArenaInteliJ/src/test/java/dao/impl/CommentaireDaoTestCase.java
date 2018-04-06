package dao.impl;

import dao.CommentaireDao;
import entities.Commentaire;
import managers.EvenementLibrary;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CommentaireDaoTestCase {


        private CommentaireDao commentaireDao=new CommentaireDaoImpl();

        @Before
        public void initDb() throws Exception {
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement stmt = connection.createStatement()) {
               /*stmt.executeUpdate("DELETE FROM projetarena.utilisateur");
                stmt.executeUpdate("DELETE FROM projetarena.evenement");
                stmt.executeUpdate("DELETE FROM projetarena.evut");

                stmt.executeUpdate("INSERT INTO projetarena.utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom1','prenom1','pseudo1','mdp1','test1@exemple','H41',1)");
                stmt.executeUpdate("INSERT INTO projetarena.utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom2','prenom2','pseudo2','mdp2','test2@exemple','H42',0)");
                stmt.executeUpdate("INSERT INTO projetarena.utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom3','prenom3','pseudo3','mdp3','test3@exemple','H43',1)");


                stmt.executeUpdate("INSERT INTO projetarena.evenement(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even1','descri1','2018-02-19','ps4',1,1)");
                stmt.executeUpdate("INSERT INTO projetarena.evenement(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even2','descri2','2018-02-18','ordi',0,1)");
                stmt.executeUpdate("INSERT INTO projetarena.evenement(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even3','descri3','2018-02-17','xbox',1,0)");


               stmt.executeUpdate("INSERT INTO projetarena.evut(`id`,`pseudo`,`paye`) VALUES ('1','pseudo1',1)");
                stmt.executeUpdate("INSERT INTO projetarena.evut(`id`,`pseudo`,`paye`) VALUES ('2','pseudo2',0)");
                stmt.executeUpdate("INSERT INTO projetarena.evut(`id`,`pseudo`,`paye`) VALUES ('3','pseudo3',0)");*/
            }
        }

        @Test
        public void shouldListCommentaire() {
            List<Commentaire> commentaire=commentaireDao.listeCommentaires();
            assertThat(commentaire).hasSize(2);
            assertThat(commentaire).extracting("idevut","pseudo","commentaire").containsOnly(tuple(1,"pseudo1","commentaire1"), tuple(2,"pseudo2","commentaire2"));
        }
        @Test
        public void shouldGetCommentaire() {
            // WHEN
            Commentaire commentaire = commentaireDao.getCommentaire("pseudo1",1);
            // THEN
            assertThat(commentaire).isNotNull();
            assertThat(commentaire.getPseudo()).isEqualTo("pseudo1");
            assertThat(commentaire.getId()).isEqualTo(1);
            assertThat(commentaire.getCommentaire()).isEqualTo("commentaire1");
        }


        @Test
        public void shouldNotGetUnknownCommentaire() {
            // WHEN
            Commentaire commentaire = commentaireDao.getCommentaire("pseudo-1",9);
            // THEN
            assertThat(commentaire).isNull();
        }

        @Test
        public void shouldAddCommentaire() throws Exception {

            Commentaire commentaire=new Commentaire(Integer.parseInt(EvenementLibrary.getInstance().getId("even3", LocalDate.of(2018,02,17))),"pseudo3","commentaire3");
            // WHEN
            Commentaire createdCommentaire=commentaireDao.addCommentaire(commentaire);

            assertThat(createdCommentaire).isNotNull();
            assertThat(createdCommentaire.getId()).isEqualTo(3);
            assertThat(createdCommentaire.getPseudo()).isEqualTo("pseudo3");
            assertThat(createdCommentaire.getCommentaire()).isEqualTo("commentaire3");

            // THEN
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM projetarena.commentaire WHERE pseudo = 'pseudo3'")) {
                    assertThat(rs.next()).isTrue();
                    assertThat(rs.getInt("idevut")).isEqualTo(3);
                    assertThat(rs.getString("pseudo")).isEqualTo("pseudo3");

                    assertThat(rs.getString("commentaire")).isEqualTo("commentaire3");

                    assertThat(rs.next()).isFalse();
                }
            }
        }
        @Test
        public void shouldDeleteCommentaire()  throws Exception {
            // WHEN

            commentaireDao.deleteCommentaire("pseudo3",3);
            // THEN
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM commentaire WHERE pseudo='pseudo3'")) {
                    assertThat(rs.next()).isFalse();
                }
            }

        }
    }






