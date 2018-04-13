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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@WebServlet("/PAEnvoyerMailEve")
public class PAEnvoyerMailEve extends HttpServlet{





    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/prive/");
        templateResolver.setSuffix(".html");
        String identifConnecte = (String) req.getSession().getAttribute("pseudo");

        /*if ("Administateur".equals(identifConnecte)) {*/
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        //ces variables seront utilisées pour afficher les evenements et les commentaires à l'aide de thymeleaf
        List<Evenement> evenementList = EvenementLibrary.getInstance().listeEvenement();
        context.setVariable("evenementList", evenementList);

        //ces variables seront utilisées pour afficher les evenements à l'aide de thymeleaf
        List<Utilisateur> utilListe2 = (List<Utilisateur>) req.getSession().getAttribute("utilListe2");
        context.setVariable("utilList", utilListe2);

        Evenement evenCh=(Evenement) req.getSession().getAttribute("evenCh");
        context.setVariable("evenCh",evenCh);

        PrintWriter out = resp.getWriter();

//on  renvoie l'utilisateur vers la page html
        templateEngine.process("PAEnvoyerMailEve", context, resp.getWriter());

        /*} else {
            resp.sendRedirect("/Profil");
        }*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom_epi = req.getParameter("nom_eve");
        String DateAsString = req.getParameter("dateEvenement");
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateEvenement = LocalDate.parse(DateAsString, dateFormat2);

        int id= EvenementLibrary.getInstance().getId(nom_epi,dateEvenement);

        Evenement evenCh=EvenementLibrary.getInstance().getEvenement(id);
        req.getSession().setAttribute("evenCh",evenCh);
        try {
            List<Utilisateur> utilListe2= EvUtLibrary.getInstance().listeEvutMail(id);
            req.getSession().setAttribute("utilListe2",utilListe2);


        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());resp.sendRedirect("/Erreur");
        }resp.sendRedirect("/PAEnvoyerMailEve");

    }
}




