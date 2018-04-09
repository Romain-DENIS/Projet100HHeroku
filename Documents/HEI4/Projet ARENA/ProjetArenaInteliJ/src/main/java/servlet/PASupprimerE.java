package servlet;

import entities.Evenement;
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
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/PASupprimerE")
public class PASupprimerE extends HttpServlet {

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


                //ces variables seront utilisées pour afficher les evenements à l'aide de thymeleaf
                List<Evenement> evenementList = EvenementLibrary.getInstance().listeEvenement();
                context.setVariable("evenementList", evenementList);


                PrintWriter out = resp.getWriter();

//on  renvoie l'utilisateur vers la page html
                templateEngine.process("PASupprimerE", context, resp.getWriter());


            } else {
                resp.sendRedirect("/Profil");
            }
        }


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //récupere les données du formulaire et de la session pour suprimer un evenement avec la commande deleteevenement
            String nom_epi = req.getParameter("nom_eve");
            String DateAsString = req.getParameter("dateEvenement");
            DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateEvenement = LocalDate.parse(DateAsString, dateFormat2);

            try {
                EvenementLibrary.getInstance().deleteEvenement(nom_epi,dateEvenement);

            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());resp.sendRedirect("/Erreur");
            }resp.sendRedirect("/Accueil");
        }
    }






