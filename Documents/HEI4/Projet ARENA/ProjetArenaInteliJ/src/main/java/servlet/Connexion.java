package servlet;

import javax.servlet.annotation.WebServlet;


import managers.UtilisateurLibrary;
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

@WebServlet("/Connexion")
public class Connexion  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out=resp.getWriter();
        out.println("            <!DOCTYPE html>");
        out.println("<html>");
        out.println("<title>Connexion</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("                    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("                    <link rel=\"stylesheet\" href=\"../CSS/Site.css\">");
        out.println("                    <body>");
        out.println("");
        out.println("<!-- Navbar -->");
        out.println("<div class=\"arena-top\">");
        out.println("                    <div class=\"arena-bar arena-white arena-wide arena-padding arena-card\">");
        out.println("                    <a href=\"Accueil\" class=\"arena-bar-item arena-button\" ><b>ARENA</b> HEI</a>");
        out.println("                    <!-- Float links to the right. Hide them on small screens -->");
        out.println("    <div class=\"arena-right arena-hide-small\">");
        out.println("                    <a href=\"Evenement\" class=\"arena-bar-item arena-button\">Evènements</a>");
        out.println("                    <a href=\"Resultat\" class=\"arena-bar-item arena-button\">Résultats</a>");
        out.println("                    <a href=\"Contact\" class=\"arena-bar-item arena-button\">Contact</a>");
        out.println("                    <a href=\"Connexion\" class=\"arena-bar-item arena-button active\">Connexion</a>");
        out.println("                    </div>");
        out.println("  </div>");
        out.println("</div>");
        out.println("<!-- Header -->");
        out.println("<header class=\"arena-display-container arena-content arena-wide\" style=\"max-width:1500px;\" id=\"home\">");
        out.println("                    <img class=\"arena-image\" src=\"../images/Connexion.jpg\" alt=\"fondecran\" width=\"100%\" height=\"auto\">");
        out.println("                    <div class=\"arena-display-middle arena-margin-top arena-center\">");
        out.println("                    <h1 class=\"arena-xxlarge arena-text-white\"><span class=\"arena-hide-small arena-text-light-grey\"><b><span class=\"arena-text-white shadow\">CONNEXION</span></b></h1>");
        out.println("                    </div>");
        out.println("</header>");
        out.println("");
        out.println("<!-- Page content -->");
        out.println("  <!-- Connexion Section -->");
        out.println("  <div class=\"arena-container arena-padding-32\" id=\"Connexion\">");
        out.println("                    <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\">Connexion</h3>");
        out.println("                    <p>La connexion permet l'inscription aux évènements et la possibilité d'être informé des évènements a venir.</p>");
        out.println("    <form  method=\"post\">");
        out.println("                    <input class=\"arena-input\" type=\"text\" placeholder=\"Pseudo\" required name=\"pseudo\">");
        out.println("                    <input class=\"arena-input\" type=\"password\" placeholder=\"Mot de passe\" required name=\"mot_de_passe\">");
        out.println("                    <input class=\"arena-button arena-black arena-section\" type=\"submit\" value=\"Se Connecter\">");

        out.println("    </form>");
        out.println("  </div>");
        out.println("<div class=\"arena-container\">");
        out.println("      <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\"> Pas encore inscrit?</h3>");
        out.println("      <a href=\"Inscription\" class=\"arena-button arena-black arena-section\"> Inscription</a>");
        out.println("      </div>");
        out.println("</body>");
        out.println("</html>");




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //permet de se connecter en verifiant que le pseudo et le mot de passe correspondent bien
        String pseudo1=req.getParameter("pseudo");
        String password1= UtilisateurLibrary.getInstance().getMotDePasse(pseudo1);
        String password2=req.getParameter("mot_de_passe");

        if (password1==null){
            password1="";

        }

        if (password1.equals(password2)) {
            req.getSession().setAttribute("pseudo",pseudo1);
            req.getSession().setAttribute("mot_de_passe",password2);
            resp.sendRedirect("/Accueil");
        } else{

            resp.sendRedirect("/Erreur");



        }

    }
}
