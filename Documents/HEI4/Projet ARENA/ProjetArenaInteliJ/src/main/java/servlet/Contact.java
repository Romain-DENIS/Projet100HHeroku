package servlet;


import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Contact")
public class Contact  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//recuperation du pseudo pour verifier si un utilisateur est connecté
        String identifConnecte = (String) req.getSession().getAttribute("pseudo");


            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
            templateResolver.setPrefix("/WEB-INF/templates/");
            templateResolver.setSuffix(".html");

            WebContext context = new WebContext(req, resp, req.getServletContext());

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

//si l'utilisateur n'est pas connecte on le renvoie vers la page html

            PrintWriter out=resp.getWriter();
            out.println("            <!DOCTYPE html>");
            out.println("<html>");
            out.println("<title>Contact</title>");
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
        out.println("                    <a href=\"Contact\" class=\"arena-right arena-bar-item arena-button active\">Contact</a>");
        out.println("                    <a href=\"Resultat\" class=\"arena-right arena-bar-item arena-button\">Résultats</a>");
        out.println("                    <a href=\"Evenement\" class=\"arena-right arena-bar-item arena-button \">Evènements</a>");
        out.println("                     <a href=\"javascript:void(0);\" style=\"font-size:15px;\" class=\"icon arena-bar-item arena-button\" onclick=\"myFunction()\">&#9776;</a>");
        out.println("  </div>");
        out.println("</div>");

            out.println("");
            out.println("<!-- Header -->");
            out.println("<header class=\"arena-display-container arena-content arena-wide\" style=\"max-width:1500px;\" id=\"home\">");
            out.println("                    <img class=\"arena-image\" src=\"../images/navi.png\" alt=\"navi\" width=\"20%\" height=\"auto\" style=\" margin-left: 23%; margin-top: 5%\">");
            out.println("                    <div class=\"arena-display-middle arena-margin-top arena-center\">");
            out.println("                    <h1 class=\"arena-xxlarge arena-text-black\"> <span class=\"arena-hide-small arena-text-black\"><b>CONTACTEZ-NOUS</b></h1>");
            out.println("                    </div>");
            out.println("</header>");
            out.println("");
            out.println("<!-- Page content -->");
            out.println("<div class=\"arena-content arena-padding\" style=\"max-width:1564px\">");
            out.println("");
            out.println("");
            out.println("                    <!-- Contact Section -->");
            out.println("  <div class=\"arena-container arena-padding-32\" id=\"contact\">");
            out.println("");
            out.println("                    <p>Pour contacter les responsables de l'association ARENA HEI, vous pouvez utiliser ces différents moyens de communication:");
            out.println("                    </p>");
            out.println("  </div>");
            out.println(" <div class=\"arena-row-padding arena-grayscale\">");
            out.println("    <div class=\"arena-col l3 m6 arena-margin-bottom\">");

            out.println("      <h3>Cassandra Lecointe</h3>");
            out.println("      <p class=\"arena-opacity\">Pr&eacutesidente</p>");
            out.println("");
            out.println("      <p><button class=\"arena-button arena-light-grey arena-block\"><a class=\"arena-button\" href=\"mailto:cassandra.lecointe@hei.yncrea.fr\">Contactez-nous</a></button></p>");
            out.println("    </div>");
            out.println("    <div class=\"arena-col l3 m6 arena-margin-bottom\">");
            out.println("      <h3>Cl&eacutement Morand</h3>");
            out.println("      <p class=\"arena-opacity\">Vice pr&eacutesident</p>");
            out.println("");
            out.println("      <p><button class=\"arena-button arena-light-grey arena-block\"><a class=\"arena-button\" href=\"mailto:clement.morand@hei.yncrea.fr\">Contactez-nous</a></button></p>");
            out.println("    </div>");
            out.println("    <div class=\"arena-col l3 m6 arena-margin-bottom\">");
            out.println("      <h3>F&eacutelix Mauduit</h3>");
            out.println("      <p class=\"arena-opacity\">Tr&eacutesorier</p>");
            out.println("");
            out.println("      <p><button class=\"arena-button arena-light-grey arena-block\"><a class=\"arena-button\" href=\"mailto:felix.mauduit@hei.yncrea.fr\">Contactez-nous</a></button></p>");
            out.println("    </div>");
            out.println("    <div class=\"arena-col l3 m6 arena-margin-bottom\">");
            out.println("      <h3>Julien Derouck</h3>");
            out.println("      <p class=\"arena-opacity\">Responsable communication</p>");
            out.println("");
            out.println("      <p><button class=\"arena-button arena-light-grey arena-block\"><a class=\"arena-button\" href=\"mailto:julien.derouck@hei.yncrea.fr\">Contactez-nous</a></button></p>");
            out.println("    </div><div class=\"arena-col l3 m6 arena-margin-bottom\">");
            out.println("    <h3>Corentin Deloffre</h3>");
            out.println("    <p class=\"arena-opacity\">Responsable HEARTHSTONE</p>");
            out.println("");
            out.println("    <p><button class=\"arena-button arena-light-grey arena-block\"><a class=\"arena-button\" href=\"mailto:corentin.deloffre@hei.yncrea.fr\">Contactez-nous</a></button></p>");
            out.println("  </div>");
            out.println("");
            out.println("    <div class=\"arena-col l3 m6 arena-margin-bottom\">");
            out.println("      <h3>Nicolas Humblot</h3>");
            out.println("      <p class=\"arena-opacity\">Responsable FIFA</p>");
            out.println("");
            out.println("      <p><button class=\"arena-button arena-light-grey arena-block\"><a class=\"arena-button\" href=\"mailto:nicolas.humblot@hei.yncrea.fr\">Contactez-nous</a></button></p>");
            out.println("    </div>");

            out.println("  </div>");
            out.println("<!-- End page content -->");
            out.println("</div>");
            out.println("");
            out.println("<!-- Footer -->");
            out.println("<footer class=\"arena-center arena-black arena-padding-16\">");
            out.println("                    <p> Site web réalisé pour l'association ARENA HEI</p>");
            out.println("                    </footer>");
            out.println(" <script>");
            out.println("         function myFunction() {");
            out.println("     var x = document.getElementById(\"myTopnav\");");
            out.println("     if (x.className === \"topnav\") {");
            out.println("         x.className += \" responsive\";");
            out.println("     } else {");
            out.println("         x.className = \"topnav\";");
            out.println("     }");
            out.println(" }");
            out.println(" </script>");
            out.println("</body>");
            out.println("</html>");



    }
}
