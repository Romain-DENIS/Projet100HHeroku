package servlet;

import entities.Evenement;
import managers.EvenementLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet("/PAAjouterE")
public class PAAjouterE extends HttpServlet{



        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String identifConnecte = (String) req.getSession().getAttribute("pseudo");

            if ("Administateur".equals(identifConnecte)) {
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
                out.println("                <a href=\"ProfilAdmin\" class=\"arena-right arena-bar-item arena-button arena-padding-24\">Retour</a>");
                out.println("                <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\">Profil Admin</h3>");
                out.println("");
                out.println("</div>");
                out.println("");
                out.println("<div class=\"arena-container arena-padding-32\">");
                out.println("                <h4 class=\"arena-padding-24  arena-border-light-grey arena-border-bottom\">Ajouter un évènement</h4>");
                out.println("                <div class=\"arena-container\">");
                out.println("                <form method=\"post\">");
                out.println("                <label><div class=\"arena-padding-16\">Nom de l'évènement: </div><input type=\"text\" name=\"nomE\" required/></label>");
                out.println("                <label><div class=\"arena-padding-16\">Description: </div><textarea name=\"descri\" placeholder=\"Description de l'évènement...\" required/></textarea></label>");
                out.println("                <label><div class=\"arena-padding-16\">Date: </div><input type=\"date\" name=\"dateE\" required/></label>");
                out.println("                <label><div class=\"arena-padding-16\">Plateforme: </div><input type=\"text\" name=\"plateforme\" required/></label>");
                out.println("                <label><div class=\"arena-padding-16\">Evènement inter-HEI: <input type=\"checkbox\" value=\"true\" name=\"interhei\"/></div></label>");
                out.println("                <label><div class=\"arena-padding-16\">Payant: <input type=\"text\" name=\"payant\"/></div></label>");
                out.println("                <input type=\"submit\" value=\"Ajouter l'évènement\"/>");
                out.println("                </form>");
                out.println("    </div>");
                out.println("</div>");
                out.println("");


                out.println("</body>");
                out.println("</html>");


            } else {
                resp.sendRedirect("/Profil");
            }
        }


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//récupere les données du formulaire et de la session pour ecrire un evenement avec la commande addevenement
            String nomE = req.getParameter("nomE");
            String descri = req.getParameter("descri");
            String releaseDateAsString = req.getParameter("dateE");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateE = LocalDate.parse(releaseDateAsString, dateFormat);
            String plateforme = req.getParameter("plateforme");
            Boolean interhei=Boolean.valueOf(req.getParameter("interhei"));
            int payant= Integer.valueOf(req.getParameter("payant"));

            entities.Evenement evenement = new Evenement(nomE, descri, dateE, plateforme, interhei, payant);
            try {
                EvenementLibrary.getInstance().addEvenement(evenement);
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());resp.sendRedirect("/Erreur");
            }
            resp.sendRedirect("/Evenement");
        }
    }








