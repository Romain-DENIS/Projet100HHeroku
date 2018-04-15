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
import java.util.Date;

@WebServlet("/Accueil")
public class Accueil extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//recuperation du pseudo pour verifier si un utilisateur est connecté
        String identifConnecte = (String) req.getSession().getAttribute("pseudo");

        String format = "yy-MM-dd";

        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format );
        java.util.Date date = new java.util.Date();

        System.out.println( formater.format( date ) );

        if (identifConnecte == null || "".equals(identifConnecte)) {
            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
            templateResolver.setPrefix("/WEB-INF/templates/");
            templateResolver.setSuffix(".html");

            WebContext context = new WebContext(req, resp, req.getServletContext());

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
//si l'utilisateur n'est pas connecte on le renvoie vers la page html
            templateEngine.process("Accueil", context, resp.getWriter());
        } else {
            PrintWriter out = resp.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<title>Accueil</title>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("                <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            out.println("                <link rel=\"stylesheet\" href=\"../CSS/Site.css\">");
            out.println("<script type=\"text/javascript\" src=\"../../js/js.js\"></script>");
            out.println("                <body>");
            out.println("");
            out.println("<!-- Navbar -->");
            out.println("<div class=\"arena-top\">");
            out.println("                    <div class=\"arena-bar arena-white arena-wide arena-padding arena-card topnav\" id=\"myTopnav\">");
            out.println("                    <a href=\"Accueil\" class=\"arena-bar-item arena-button active\" ><b>ARENA</b> HEI</a>");
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

            out.println("");
            out.println("<!-- Header -->");
            out.println("<header class=\"arena-display-container arena-content arena-wide\" style=\"max-width:1500px;\" id=\"home\">");
            out.println("                <img class=\"arena-image\" src=\"../images/fondecran.jpg\" alt=\"fondecran\" width=\"100%\" height=\"auto\">");
            out.println("                <div class=\"arena-display-middle arena-margin-top arena-center\">");
            out.println("                <h1 class=\"arena-xxlarge arena-text-white\"><img id=\"logoA\" src=\"../images/ARENA.png\"/ width=\"35%\" height=\"auto\"> </span> <span class=\"arena-hide-small arena-text-light-grey shadow\"><b>ARENA</br> <span class=\"arena-hide-small arena-text-light-red shadow\">HEI</span></b></h1>");
            out.println("                </div>");
            out.println("</header>");
            out.println("");
            out.println("<!-- Page content -->");
            out.println("<div class=\"arena-content arena-padding\" style=\"max-width:1564px\">");
            out.println("");
            out.println("                <div class=\"arena-container arena-padding-32\" id=\"presentation\">");
            out.println("                <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\">Présentation</h3>");
            out.println("                <p>L'association ARENA HEI est une association organisant divers évènements d'e-sport comme des LANS, tournois ou rediffusion de parties de jeux videos tels que les LCS (Les League of Legends Championship Series) ou d'autres évènements mondiaux dans le domaine des jeux vidéos.");
            out.println("                </p>");
            out.println("  </div>");
            out.println("<div class=\"arena-padding-64\"></div>");
            out.println("<!-- End page content -->");
            out.println("</div>");
            out.println("");
            out.println("<!-- Footer -->");
            out.println("<footer class=\"arena-center arena-black arena-padding-16\">");
            out.println("                <p> Site web réalisé pour l'association ARENA HEI</p>");
            out.println("                </footer>");

            out.println("");
            out.println("</body>");
            out.println("</html>");


        }

    }

}
