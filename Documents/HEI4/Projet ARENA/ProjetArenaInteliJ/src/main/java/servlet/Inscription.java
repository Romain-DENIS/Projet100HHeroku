package servlet;

import javax.servlet.annotation.WebServlet;


import entities.Utilisateur;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/Inscription")
public class Inscription  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            PrintWriter out=resp.getWriter();
out.println("            <!DOCTYPE html>");
out.println("<html>");
out.println("<title>Inscription</title>");
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
out.println("                    <a href=\"Connexion\" class=\"arena-bar-item arena-button active\">Inscription</a>");
out.println("                    </div>");
out.println("  </div>");
out.println("</div>");
out.println("<!-- Header -->");
out.println("<header class=\"arena-display-container arena-content arena-wide\" style=\"max-width:1500px;\" id=\"home\">");
out.println("                    <img class=\"arena-image\" src=\"../images/Connexion.jpg\" alt=\"fondecran\" width=\"100%\" height=\"auto\">");
out.println("                    <div class=\"arena-display-middle arena-margin-top arena-center\">");
out.println("                    <h1 class=\"arena-xxlarge arena-text-white\"><span class=\"arena-hide-small arena-text-light-grey\"><b><span class=\"arena-text-white shadow\">INSCRIPTION</span></b></h1>");
out.println("                    </div>");
out.println("</header>");
out.println("");
out.println("<!-- Page content -->");
out.println("  <!-- Connexion Section -->");
out.println("  <div class=\"arena-container arena-padding-32\" id=\"Connexion\">");
out.println("                    <h3 class=\"arena-border-bottom arena-border-light-grey arena-padding-16\">Inscription</h3>");
out.println("                    <p>Veuillez renseigner les différents champs afin de vous inscrire. Le pseudo et le mot de passe seront utilisés pour se connecter.</p>");
out.println("    <form method=\"post\">");
out.println("                    <input class=\"arena-input arena-section\" type=\"text\" placeholder=\"Nom\" required name=\"nom\" onKeyPress=\"return verifieChar(event,0);\">");
out.println("                    <input class=\"arena-input arena-section\" type=\"text\" placeholder=\"Prenom\" required name=\"prenom\" onKeyPress=\"return verifieChar(event,0);\">");
out.println("                    <input class=\"arena-input arena-section\" type=\"text\" placeholder=\"Email\" required name=\"email\" pattern=\".+@hei.yncrea.fr\" title=\"Merci de fournir uniquement une adresse hei.yncrea.fr\">");
out.println("                    <input class=\"arena-input arena-section\" type=\"text\" placeholder=\"Classe\" required name=\"classe\" size=\"4\" pattern=\"H+..\" title=\"Merci de selectionner une classe de type H.. suivi du numero de votre classe\">");
        out.println("                    <input class=\"arena-input arena-section\" type=\"text\" placeholder=\"Pseudo\" required name=\"pseudo\">");
        out.println("                    <input class=\"arena-input arena-section\" type=\"password\" placeholder=\"Mot de passe\" required name=\"mot_de_passe\">");
out.println("                    <div class=\"arena-input arena-section\" ><input type=\"checkbox\" value=\"true\" id=\"Notif\" name=\"notif\"><label for=\"Notif\">Souhaitez-vous vous abonner à la newsletter ?</label></div>");
out.println("                    <input class=\"arena-button arena-black arena-section\" type=\"submit\" value=\"S'inscrire\">");
out.println("    </form>");
out.println("  </div>");
out.println("<script type=\"text/javascript\" language=\"javascript\">");
out.println(" ");
out.println(" /*Fonction qui vérifie que le caractère inscrit est alphabétique");
out.println("  @param evenement est l'événement fournis par le keypress");
out.println("  @param type est le type de caractère qu'on souhaite bloquer: 0 pour bloquer chiffres, 1 pour bloquer lettres");
out.println("  @return true si le caractère est correct");
out.println(" */");
out.println("                function verifieChar(evenement,type){");
out.println("            var charCode;");
out.println("            charCode = evenement.keyCode; //Code ascii");
out.println("");
out.println("            switch(type){");
out.println("                case 0:");
out.println("                    //Lettres en majuscules,minuscule et trait d'union");
out.println("                    if((charCode >= 65 && charCode <= 90)");
out.println("                            ||(charCode >= 97 && charCode <= 122)");
out.println("                            ||(charCode == 45)");
out.println("                            ||(charCode == 32)){");
out.println("");
out.println("                        return true ;");
out.println("                    }");
out.println("                    //si c'est un chiffre ou autre on n'affiche rien");
out.println("                    else{");
out.println("                        return false ;");
out.println("                    }");
out.println("                    break;");
out.println("                case 1:");
out.println("                    //chiffres et trait d'union");
out.println("                    if((charCode >= 48 && charCode <= 57)");
out.println("                            ||(charCode == 45)");
out.println("                            ||(charCode == 32)){");
out.println("");
out.println("                        return true;");
out.println("                    }");
out.println("                    //si c'est autre chose on n'affiche rien");
out.println("                    else{");
out.println("                        return false ;");
out.println("                    }");
out.println("                    break;");
out.println("                case 2:");
out.println("                    //Lettres et chiffres seulement");
out.println("                    if((charCode >= 65 && charCode <= 90)");
out.println("                            ||(charCode >= 97 && charCode <= 122)");
out.println("                            ||(charCode >= 48 && charCode <= 57)");
out.println("                            ||(charCode == 32)){");
out.println("");
out.println("                        return true;");
out.println("                    }");
out.println("                    else{");
out.println("                        return false;");
out.println("                    }");
out.println("                    break;");
out.println("            }//fermeture du switch");
out.println("        }//fermeture de la fonction");
out.println("</script>");
            out.println("</body>");
            out.println("</html>");




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//récuperation du formulaire pour inscrire un utilisateur avec addUtilisateur
        String pseudo = req.getParameter("pseudo");
        String mot_de_passe = req.getParameter("mot_de_passe");
        String email=req.getParameter("email");
        String nom=req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String classe=req.getParameter("classe");
        Boolean notif= Boolean.valueOf(req.getParameter("notif"));

        String pseudoV=UtilisateurLibrary.getInstance().getPseudo(pseudo);
        Utilisateur utilisateur= new Utilisateur(nom,prenom,pseudo,mot_de_passe,email,classe,notif);
        if(pseudo.equals(pseudoV)){
            resp.sendRedirect("/Erreur");
        }else
        {
            try {
                UtilisateurLibrary.getInstance().addUtilisateur(utilisateur);
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());
                resp.sendRedirect("/Erreur");
            }
            resp.sendRedirect("/Accueil");
        }
    }
}
