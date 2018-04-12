package servlet;


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
import java.util.List;


@WebServlet("/PASupprimerU")
public class PASupprimerU extends HttpServlet{





        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
            templateResolver.setPrefix("/WEB-INF/templates/prive/");
            templateResolver.setSuffix(".html");
            String identifConnecte = (String) req.getSession().getAttribute("pseudo");

           /* if ("Administateur".equals(identifConnecte)) {*/
                WebContext context = new WebContext(req, resp, req.getServletContext());

                TemplateEngine templateEngine = new TemplateEngine();
                templateEngine.setTemplateResolver(templateResolver);


                //ces variables seront utilisées pour afficher les evenements à l'aide de thymeleaf
                List<Utilisateur> utilList = UtilisateurLibrary.getInstance().listeUtilisateur();
                context.setVariable("utilList", utilList);


                PrintWriter out = resp.getWriter();

//on  renvoie l'utilisateur vers la page html
                templateEngine.process("PASupprimerU", context, resp.getWriter());

          /*  } else {
                resp.sendRedirect("/Profil");
            }*/

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//récupere les données du formulaire et de la session pour supprimer un utilisateru avec la commande deleteutilisateur
            String pseudo=req.getParameter("pseudo");

            try {
                UtilisateurLibrary.getInstance().deleteUtilisateur(pseudo);
                resp.sendRedirect("/Accueil");
            } catch (IllegalArgumentException e) {
                req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());resp.sendRedirect("/Erreur");
            }
        }
}




