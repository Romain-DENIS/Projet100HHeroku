package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/ProfilAdmin")
public class ProfilAdmin extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identifConnecte = (String) req.getSession().getAttribute("pseudo");

        /*if ("Administateur".equals(identifConnecte)) {*/
            PrintWriter out = resp.getWriter();
            out.println("        <!DOCTYPE html>");
            out.println("<html>");
            out.println("<title>ProfilAdmin</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("                <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            out.println("                <link rel=\"stylesheet\" href=\"../CSS/Site.css\">");
            out.println("                <body>");
            out.println("");
            out.println("<!-- Navbar -->");
            out.println("<div class=\"arena-top\">");
            out.println("                <div class=\"arena-bar arena-white arena-wide arena-padding arena-card\">");
            out.println("                <a href=\"Accueil\" class=\"arena-bar-item arena-button\" ><b>ARENA</b> HEI</a>");
            out.println("                <!-- Float links to the right. Hide them on small screens -->");
            out.println("        <div class=\"arena-right arena-hide-small\">");
            out.println("                <a href=\"Evenement\" class=\"arena-bar-item arena-button\">Evènements</a>");
            out.println("                <a href=\"Resultat\" class=\"arena-bar-item arena-button\">Résultats</a>");
            out.println("                <a href=\"Contact\" class=\"arena-bar-item arena-button\">Contact</a>");
            out.println("                <a href=\"ProfilAdmin\" class=\"arena-bar-item arena-button active\">Profil Admin</a>");
            out.println("                </div>");
            out.println("    </div>");
            out.println("</div>");
            out.println("<!-- Header -->");
            out.println("<header class=\"arena-display-container arena-content arena-wide\" style=\"max-width:1500px;\" id=\"home\">");
            out.println("                <img class=\"arena-image\" src=\"../images/ProfilAdmin.jpg\" alt=\"fondecran\" width=\"100%\" height=\"auto\">");
            out.println("                <div class=\"arena-display-middle arena-margin-top arena-center\">");
            out.println("                <h1 class=\"arena-xxlarge arena-text-white\"><span class=\"arena-hide-small arena-text-light-grey\"><b><span class=\"arena-text-white shadow\">ADMINISTRATEUR</span></b></span></h1>");
            out.println("                </div>");
            out.println("</header>");
            out.println("");
            out.println("<!-- Page content -->");
            out.println("");
            out.println("");
            out.println("<div class=\"arena-container arena-padding-32\" id=\"presentation\">");
            out.println("                <a href=\"Deconnexion\" class=\"arena-right arena-bar-item arena-button arena-padding-24\">Se déconnecter</a>");
            out.println("                <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\">Profil Admin</h3>");
            out.println("");
            out.println("");
            out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
            out.println("                <a href=\"PAEnvoyerMail\" class=\" arena-border arena-button arena-padding-24\">Obtenir les adresses mails des utilisateurs</a>");
            out.println("</div>");
        out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
        out.println("                <a href=\"PAEnvoyerMailEve\" class=\" arena-border arena-button arena-padding-24\">Obtenir les adresses mails des utilisateurs pour un évènement</a>");
        out.println("</div>");
            out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
            out.println("                <a href=\"PASupprimerU\" class=\" arena-border arena-button arena-padding-24\">Supprimer un utilisateur</a>");
            out.println("</div>");
            out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
            out.println("                <a href=\"PAAjouterE\" class=\" arena-border arena-button arena-padding-24\">Ajouter un évènement</a>");
            out.println("</div>");
            out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
            out.println("");
            out.println("                <a href=\"PASupprimerE\" class=\" arena-border arena-button arena-padding-24\">Supprimer un évènement</a>");
            out.println("</div>");
            out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
            out.println("");
            out.println("                <a href=\"PAAfficherInscris\" class=\" arena-border arena-button arena-padding-24\">Afficher les inscrits d'un evenement</a>");
            out.println("</div>");
            out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
            out.println("");
            out.println("                <a href=\"PAAfficherPlateforme\" class=\" arena-border arena-button arena-padding-24\">Afficher les inscrits pour un type de plateforme</a>");
            out.println("</div>");
            out.println("                <div class=\"arena-container arena-center arena-padding-32\">");
            out.println("");
            out.println("                <a href=\"PAAfficherPrix\" class=\" arena-border arena-button arena-padding-24\">Afficher les revenus d'un evenement</a>");
            out.println("</div>");
            out.println("");
            out.println("");
            out.println("</body>");
            out.println("</html>");


        /*}else{
            resp.sendRedirect("/Profil");
        }*/
    }

}