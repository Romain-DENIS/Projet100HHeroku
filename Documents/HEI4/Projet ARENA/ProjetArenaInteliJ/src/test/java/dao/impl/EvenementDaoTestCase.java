package dao.impl;

import dao.EvenementDao;
import entities.Evenement;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class EvenementDaoTestCase {


        private EvenementDao evenementDao=new EvenementDaoImpl();

        @Before
        public void initDb() throws Exception {
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement stmt = connection.createStatement()) {
                /*stmt.executeUpdate("DELETE FROM evenement");
                stmt.executeUpdate("INSERT INTO `evenement`(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even1','descri1','2018-02-19','ps4',1,1)");
                stmt.executeUpdate("INSERT INTO `evenement`(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even2','descri2','2018-02-18','ordi',0,1)");
                stmt.executeUpdate("INSERT INTO `evenement`(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even3','descri3','2018-02-17','xbox',1,0)");
            */
            }
        }

        @Test
        public void shouldListEvenement() {
            List<Evenement> evenement=evenementDao.listeEvenement();
            assertThat(evenement).hasSize(3);
            assertThat(evenement).extracting("nomE","descri","dateE","plateforme","interhei","payant").containsOnly(tuple("even1","descri1",LocalDate.of(2018,02,19),"ps4",true,true), tuple("even2","descri2",LocalDate.of(2018,02,18),"ordi",false,true), tuple("even3","descri3",LocalDate.of(2018,02,17),"xbox",true,false));
        }
        @Test
        public void shouldGetEvenement() {
            // WHEN
            Evenement evenement = evenementDao.getEvenement("even1");
            // THEN
            assertThat(evenement).isNotNull();
            assertThat(evenement.getNomE()).isEqualTo("even1");
            assertThat(evenement.getDescri()).isEqualTo("descri1");
            assertThat(evenement.getDate()).isEqualTo(LocalDate.of(2018,02,19));
            assertThat(evenement.getPlateforme()).isEqualTo("ps4");
            assertThat(evenement.isInterhei()).isEqualTo(true);
            assertThat(evenement.isPayant()).isEqualTo(true);
        }

        @Test
        public void shouldGetId() {
            // WHEN
            String evenement = evenementDao.getId("even1",LocalDate.of(2018,02,19));
            // THEN
            assertThat(evenement).isNotNull();
            assertThat(evenement).isEqualTo("1");

        }

        @Test
        public void shouldNotGetUnknownEvenement() {
            // WHEN
            Evenement evenement = evenementDao.getEvenement("even-1");
            // THEN
            assertThat(evenement).isNull();
        }

        @Test
        public void shouldAddEvenement() throws Exception {

            Evenement event=new Evenement("exeven","exdescri",LocalDate.of(2017,05,19),"explat",true,true);
            // WHEN
            Evenement createdEvenement=evenementDao.addEvenement(event);

            assertThat(createdEvenement).isNotNull();
            assertThat(createdEvenement.getNomE()).isEqualTo("exeven");
            assertThat(createdEvenement.getDescri()).isEqualTo("exdescri");
            assertThat(createdEvenement.getDate()).isEqualTo(LocalDate.of(2017,05,19));
            assertThat(createdEvenement.getPlateforme()).isEqualTo("explat");
            assertThat(createdEvenement.isInterhei()).isEqualTo(true);
            assertThat(createdEvenement.isPayant()).isEqualTo(true);

            // THEN
            try (Connection connection = DataSourceProvider.getDataSource().getConnection();
                 Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM evenement WHERE nomE = 'exeven'")) {
                    assertThat(rs.next()).isTrue();
                    assertThat(rs.getString("nomE")).isEqualTo("exeven");
                    assertThat(rs.getString("descri")).isEqualTo("exdescri");
                    assertThat(rs.getDate("dateE").toLocalDate()).isEqualTo(LocalDate.of(2017,05,19));
                    assertThat(rs.getString("plateforme")).isEqualTo("explat");
                    assertThat(rs.getBoolean("interhei")).isEqualTo(true);
                    assertThat(rs.getBoolean("payant")).isEqualTo(true);

                    assertThat(rs.next()).isFalse();
                }
            }
        }
    @Test
    public void shouldDeleteEvenement()  throws Exception {
        // WHEN

        evenementDao.deleteEvenement("exeven",LocalDate.of(2017,05,19));
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM evenement WHERE nomE='exeven'")) {
                assertThat(rs.next()).isFalse();
            }
        }

    }
    }


