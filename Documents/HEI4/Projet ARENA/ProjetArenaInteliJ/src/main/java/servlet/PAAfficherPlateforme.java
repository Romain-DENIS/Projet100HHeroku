package servlet;

import entities.EvUt;
import entities.Evenement;
import entities.Utilisateur;
import managers.EvUtLibrary;
import managers.EvenementLibrary;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@WebServlet("/PAAfficherPlateforme")
public class PAAfficherPlateforme extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/prive/");
        templateResolver.setSuffix(".html");
        String identifConnecte = (String) req.getSession().getAttribute("pseudo");

        if ("Administateur".equals(identifConnecte)) {
            WebContext context = new WebContext(req, resp, req.getServletContext());

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            //ces variables seront utilisées pour afficher les evenements et les commentaires à l'aide de thymeleaf
            List<Evenement> evenementList = EvenementLibrary.getInstance().listeEvenement();
            context.setVariable("evenementList", evenementList);

            List<entities.Utilisateur> UtilList = (List<entities.Utilisateur>) req.getSession().getAttribute("listeU");
            context.setVariable("UtilList", UtilList);


//on  renvoie l'utilisateur vers la page html
            templateEngine.process("PAAfficherPlateforme", context, resp.getWriter());


        } else {
            resp.sendRedirect("/Profil");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //récupere les données du formulaire et de la session pour afficher les pseudos inscris a un evenement avec la commande listeevut
        String plateforme = req.getParameter("plateforme");




        try {
            List<Utilisateur> liste= UtilisateurLibrary.getInstance().listeUtilisateurPlateforme(plateforme);
            req.getSession().setAttribute("listeU",liste);


        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());resp.sendRedirect("/Erreur");
        }resp.sendRedirect("/PAAfficherPlateforme");
    }
}

