package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Erreur")
public class Erreur extends HttpServlet {
//renvoie une erreur lors du malfonctionnement d'un formulaire

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String identifConnecte = (String) req.getSession().getAttribute("pseudo");
        PrintWriter out = resp.getWriter();
        out.println("            <!DOCTYPE html>");
        out.println("<html>");
        out.println("<title>Inscription</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("                    <link rel=\"stylesheet\" href=\"../CSS/Site.css\">");
        out.println("<script type=\"text/javascript\" src=\"../../js/js.js\"></script>");

        out.println("                    <body>");
        out.println("");
        out.println("<!-- Navbar -->");
        out.println("<div class=\"arena-top\">");
        out.println("                    <div class=\"arena-bar arena-white arena-wide arena-padding arena-card topnav\" id=\"myTopnav\">");
        out.println("                    <a href=\"Accueil\" class=\"arena-bar-item arena-button\" ><b>ARENA</b> HEI</a>");
        out.println("                    <!-- Float links to the right. Hide them on small screens -->");
        //aide à savoir qui est connecte et dirige vers la page souhaitée
        if(identifConnecte==null || "".equals(identifConnecte)) {
            out.println("<a href=\"Connexion\" class=\"arena-right arena-bar-item arena-button\">Connexion</a>");
        }else {
            if ("Administrateur".equals(identifConnecte)) {
                out.println("<a href=\"ProfilAdmin\" class=\"arena-right arena-bar-item arena-button\">Profil Admin</a>");
            } else {
                out.println("<a href=\"Profil\" class=\"arena-right arena-bar-item arena-button\">Profil</a>");
            }
        }
        out.println("                    <a href=\"Contact\" class=\"arena-right arena-bar-item arena-button\">Contact</a>");
        out.println("                    <a href=\"Resultat\" class=\"arena-right arena-bar-item arena-button\">Résultats</a>");
        out.println("                    <a href=\"Evenement\" class=\"arena-right arena-bar-item arena-button \">Evènements</a>");
        out.println("                     <a href=\"javascript:void(0);\" style=\"font-size:15px;\" class=\"icon arena-bar-item arena-button\" onclick=\"myFunction()\">&#9776;</a>");
        out.println("  </div>");
        out.println("</div>");


        out.println("<!-- Page content -->");
        out.println("  <!-- Connexion Section -->");
        out.println("  <div class=\"arena-container arena-padding-32\" id=\"Connexion\">");
        out.println("                    <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\">Erreur</h3>");
        out.println("  <p> Veuillez réessayer de remplir le formulaire en selectionnant des données valides</p></div>");
        out.println("</body>");
        out.println("</html>");
    }
}