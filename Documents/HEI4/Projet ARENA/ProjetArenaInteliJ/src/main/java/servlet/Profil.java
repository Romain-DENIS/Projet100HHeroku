package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/Profil")
public class Profil extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identifConnecte= (String) req.getSession().getAttribute("pseudo");
        PrintWriter out = resp.getWriter();
        out.println("    <!DOCTYPE html>");
        out.println("<html>");
        out.println("<title>Profil</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<link rel=\"stylesheet\" href=\"../CSS/Site.css\">");
        out.println("<body>");
        out.println("");
        out.println("<!-- Navbar -->");
        out.println("<div class=\"arena-top\">");
        out.println("    <div class=\"arena-bar arena-white arena-wide arena-padding arena-card\">");
        out.println("        <a href=\"Accueil\" class=\"arena-bar-item arena-button\" ><b>ARENA</b> HEI</a>");
        out.println("        <!-- Float links to the right. Hide them on small screens -->");
        out.println("        <div class=\"arena-right arena-hide-small\">");
        out.println("            <a href=\"Evenement\" class=\"arena-bar-item arena-button\">Evènements</a>");
        out.println("            <a href=\"Resultat\" class=\"arena-bar-item arena-button\">Résultats</a>");
        out.println("            <a href=\"Contact\" class=\"arena-bar-item arena-button\">Contact</a>");
        out.println("            <a href=\"Profil\" class=\"arena-bar-item arena-button active\">Profil</a>");
        out.println("        </div>");
        out.println("    </div>");
        out.println("</div>");
        out.println("<!-- Header -->");
        out.println("<header class=\"arena-display-container arena-content arena-wide\" style=\"max-width:1500px;\" id=\"home\">");
        out.println("    <img class=\"arena-image\" src=\"../images/Profil.png\" alt=\"fondecran\" width=\"100%\" height=\"auto\">");
        out.println("    <div class=\"arena-display-middle arena-margin-top arena-center\">");
        out.println("        <h1 class=\"arena-xxlarge arena-text-white\"><span class=\"arena-hide-small arena-text-light-grey\"><b><span class=\"arena-text-white shadow\">PROFIL</span></b></h1>");
        out.println("    </div>");
        out.println("</header>");
        out.println("");
        out.println("<!-- Page content -->");
        out.println("");
        out.println("");
        out.println("<div class=\"arena-container arena-padding-32\" id=\"presentation\">");
        //deconnexion a partir du profil
        out.println("    <a href=\"Deconnexion\" class=\"arena-right arena-bar-item arena-button arena-padding-24\">Se déconnecter</a>");
        out.println(String.format("    <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\">Profil de %s</h3>",identifConnecte));
        out.println("<div class=\"arena-container arena-padding-32\">");
        out.println("    <h5 class=\"arena-border-bottom arena-border-light-grey arena-padding-16 arena-sous-titre\">Evènements inscrits</h5>");
        out.println("    <a  class=\"arena-button arena-decalage-event\" href=\"Evènements\">Event 1 </a><br>");
        out.println("    <a  class=\"arena-button arena-decalage-event\" href=\"Evènements\">Event 2 </a><br>");
        out.println("</div>");
        out.println("<div class=\"arena-container arena-padding-32\">");
        out.println("    <h5 class=\"arena-border-bottom arena-border-light-grey arena-padding-16 arena-sous-titre\">Evènements auxquels j'ai participé</h5>");
        out.println("    <a class=\"arena-button arena-decalage-event\" href=\"Evènements\">Event 1</a><br>");
        out.println("    <a class=\"arena-button arena-decalage-event\" href=\"Evènements\">Event 2</a><br>");
        out.println("</div>");
        out.println("<div class=\"arena-container arena-padding-32\">");
        out.println("    <a href=\"Suppression\" onclick=\"return confirm(\'Etes-vous sur de vouloir supprimer votre compte?\');\")\" class=\"arena-right arena-bar-item arena-black arena-round-large arena-button arena-supprime arena-padding-24\">Supprimer mon compte</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");


    }
}
