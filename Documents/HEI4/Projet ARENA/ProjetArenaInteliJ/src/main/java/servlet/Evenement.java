package servlet;



import managers.CommentaireLibrary;
import managers.EvenementLibrary;
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
import java.time.ZoneId;
import java.util.List;

@WebServlet("/Evenement")
public class Evenement  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//recuperation du pseudo pour verifier si un utilisateur est connecté
        String identifConnecte = (String) req.getSession().getAttribute("pseudo");

            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
            templateResolver.setPrefix("/WEB-INF/templates/");
            templateResolver.setSuffix(".html");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        java.util.Date date = new java.util.Date();
        LocalDate date2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year=date2.getYear();
        int month=date2.getMonthValue();
        int day=date2.getDayOfMonth();
        context.setVariable("date2",date2);
        context.setVariable("day",day);
        context.setVariable("month",month);
        context.setVariable("year",year);

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            //ces variables seront utilisées pour afficher les evenements et les commentaires à l'aide de thymeleaf
        List<entities.Evenement> evenementList= EvenementLibrary.getInstance().listeEvenement();
        context.setVariable("evenementList",evenementList);

        List<entities.Commentaire> commentaireList= CommentaireLibrary.getInstance().listeCommentaire();
        context.setVariable("commentaireList",commentaireList);
//on  renvoie l'utilisateur vers la page html
        templateEngine.process("Evenement", context, resp.getWriter());

            PrintWriter out=resp.getWriter();
            out.println("<!-- Navbar -->");
            out.println("<div class=\"arena-top\">");
            out.println("                    <div class=\"arena-bar arena-white arena-wide arena-padding arena-card\">");
            out.println("                    <a href=\"Accueil\" class=\"arena-bar-item arena-button\" ><b>ARENA</b> HEI</a>");
            out.println("                    <!-- Float links to the right. Hide them on small screens -->");
            out.println("    <div class=\"arena-right arena-hide-small\">");
            out.println("                    <a href=\"Evenement\" class=\"arena-bar-item arena-button active\">Evènements</a>");
            out.println("                    <a href=\"Resultat\" class=\"arena-bar-item arena-button\">Résultats</a>");
            out.println("                    <a href=\"Contact\" class=\"arena-bar-item arena-button\">Contact</a>");
            //aide à savoir qui est connecte et dirige vers la page souhaitée
            if(identifConnecte==null || "".equals(identifConnecte)) {
                out.println("<a href=\"Connexion\" class=\"arena-bar-item arena-button\">Connexion</a>");
            }else {
                if ("Administrateur".equals(identifConnecte)) {
            out.println("<a href=\"ProfilAdmin\" class=\"arena-bar-item arena-button\">Profil Admin</a>");
        } else {
            out.println("<a href=\"Profil\" class=\"arena-bar-item arena-button\">Profil</a>");
        }
    }
            out.println("                    </div>");
            out.println("  </div>");
            out.println("</div>");


    }
}
