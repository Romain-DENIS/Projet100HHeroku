package dao.impl;

import dao.UtilisateurDao;
import entities.Utilisateur;
import org.junit.Before;
import org.junit.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


public class UtilisateurDaoTestCase {

    private UtilisateurDao utilisateurDao=new UtilisateurDaoImpl();

    @Before
    public void initDb() throws Exception {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
           /* stmt.executeUpdate("DELETE FROM utilisateur");
            stmt.executeUpdate("INSERT INTO `utilisateur`(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom1','prenom1','pseudo1','mdp1','test1@exemple','H41',1)");
            stmt.executeUpdate("INSERT INTO `utilisateur`(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom2','prenom2','pseudo2','mdp2','test2@exemple','H42',0)");
            stmt.executeUpdate("INSERT INTO `utilisateur`(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom3','prenom3','pseudo3','mdp3','test3@exemple','H43',1)");
       */
        }
    }

    @Test
    public void shouldListUtilisateurs() {
        List<Utilisateur> utilisateurs=utilisateurDao.listeUtilisateur();
        assertThat(utilisateurs).hasSize(4);
        assertThat(utilisateurs).extracting("nom","prenom","pseudo","mot_de_passe","email","classe","notif").containsOnly(tuple("Admin","Admin","Administrateur","ARENAHEI",".@hei.yncrea.fr","H",false),tuple("nom1","prenom1","pseudo1","mdp1","test1@exemple","H44",true), tuple("nom2","prenom2","pseudo2","mdp2","test2@exemple","H42",false), tuple("nom3","prenom3","pseudo3","mdp3","test3@exemple","H43",true));
    }
    @Test
    public void shouldGetUtilisateur() {
        // WHEN
        Utilisateur utilisateur = utilisateurDao.getUtilisateur("pseudo1");
        // THEN
        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getNom()).isEqualTo("nom1");
        assertThat(utilisateur.getPrenom()).isEqualTo("prenom1");
        assertThat(utilisateur.getPseudo()).isEqualTo("pseudo1");
        assertThat(utilisateur.getMot_de_passe()).isEqualTo("mdp1");
        assertThat(utilisateur.getEmail()).isEqualTo("test1@exemple");
        assertThat(utilisateur.getClasse()).isEqualTo("H44");
        assertThat(utilisateur.getNotif()).isEqualTo(true);
    }

    @Test
    public void shouldGetMotdePasse() {
        // WHEN
        String utilisateur = utilisateurDao.getMotDePasse("pseudo1");
        // THEN
        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur).isEqualTo("mdp1");

    }


    @Test
    public void shouldNotGetUnknownUtilisateur() {
        // WHEN
        Utilisateur utilisateur = utilisateurDao.getUtilisateur("pseudo-1");
        // THEN
        assertThat(utilisateur).isNull();
    }
    @Test
    public void shouldAddUtilisateur() throws Exception {

        Utilisateur user=new Utilisateur("exnom","exprenom","expseudo","exmdp","extest@exemple","exH",true);
        // WHEN
        Utilisateur createdUtilisateur=utilisateurDao.addUtilisateur(user);

        assertThat(createdUtilisateur).isNotNull();
        assertThat(createdUtilisateur.getNom()).isEqualTo("exnom");
        assertThat(createdUtilisateur.getPrenom()).isEqualTo("exprenom");
        assertThat(createdUtilisateur.getPseudo()).isEqualTo("expseudo");
        assertThat(createdUtilisateur.getMot_de_passe()).isEqualTo("exmdp");
        assertThat(createdUtilisateur.getEmail()).isEqualTo("extest@exemple");
        assertThat(createdUtilisateur.getClasse()).isEqualTo("exH");
        assertThat(createdUtilisateur.getNotif()).isEqualTo(true);


        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE pseudo='expseudo'")) {
                assertThat(rs.next()).isTrue();
                assertThat(rs.getString("nom")).isEqualTo("exnom");
                assertThat(rs.getString("prenom")).isEqualTo("exprenom");
                assertThat(rs.getString("pseudo")).isEqualTo("expseudo");
                assertThat(rs.getString("mot_de_passe")).isEqualTo("exmdp");
                assertThat(rs.getString("email")).isEqualTo("extest@exemple");
                assertThat(rs.getString("classe")).isEqualTo("exH");
                assertThat(rs.getBoolean("notif")).isEqualTo(true);
                assertThat(rs.next()).isFalse();
            }
        }
    }

    @Test
    public void shouldDeleteUtilisateur()  throws Exception {
        // WHEN

        utilisateurDao.deleteUtilisateur("expseudo");
        // THEN
        try (Connection connection = DataSourceProvider.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM utilisateur WHERE pseudo='expseudo'")) {
                assertThat(rs.next()).isFalse();
            }
        }

    }
}
