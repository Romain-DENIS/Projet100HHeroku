package dao.impl;

import dao.EvUtDao;
import dao.EvenementDao;
import entities.EvUt;
import entities.Evenement;
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

public class EvUtDaoTestCase {


        private EvUtDao evutDao=new EvUtDaoImpl();

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
        public void shouldListEvUt() {
            List<EvUt> evut=evutDao.listeEvUt(1);
            assertThat(evut).hasSize(1);
            assertThat(evut).extracting("id","pseudo","paye").containsOnly(tuple(1,"pseudo1",true));
        }
        @Test
        public void shouldGetEvUt() {
            // WHEN
            EvUt evut = evutDao.getEvUt("pseudo1",1);
            // THEN
            assertThat(evut).isNotNull();
            assertThat(evut.getPseudo()).isEqualTo("pseudo1");
            assertThat(evut.getId()).isEqualTo(1);
            assertThat(evut.isPaye()).isEqualTo(true);
        }


        @Test
        public void shouldNotGetUnknownEvUt() {
            // WHEN
            EvUt evut = evutDao.getEvUt("pseudo-1",9);
            // THEN
            assertThat(evut).isNull();
        }

        @Test
        public void shouldAddEvUt() throws Exception {

            EvUt evut=new EvUt(Integer.parseInt(EvenementLibrary.getInstance().getId("even3",LocalDate.of(2018,02,17))),"pseudo3",true);
            // WHEN
            EvUt createdEvUt=evutDao.addEvUt(evut);

            assertThat(createdEvUt).isNotNull();
            assertThat(createdEvUt.getId()).isEqualTo(3);
            assertThat(createdEvUt.getPseudo()).isEqualTo("pseudo3");
            assertThat(createdEvUt.isPaye()).isEqualTo(true);

            // THEN
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM projetarena.evut WHERE pseudo = 'pseudo3'")) {
                    assertThat(rs.next()).isTrue();
                    assertThat(rs.getInt("id")).isEqualTo(3);
                    assertThat(rs.getString("pseudo")).isEqualTo("pseudo3");

                    assertThat(rs.getBoolean("paye")).isEqualTo(true);

                    assertThat(rs.next()).isFalse();
                }
            }
        }
        @Test
        public void shouldDeleteEvUt()  throws Exception {
            // WHEN

            evutDao.deleteEvUt("pseudo3",3);
            // THEN
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM evut WHERE pseudo='expseudo'")) {
                    assertThat(rs.next()).isFalse();
                }
            }

        }
    }




