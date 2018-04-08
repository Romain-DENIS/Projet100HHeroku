package servlet;

import entities.EvUt;
import entities.Evenement;
import managers.EvUtLibrary;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@WebServlet("/PAAfficherPrix")
public class PAAfficherPrix extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(req.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/prive/");
        templateResolver.setSuffix(".html");

        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        //ces variables seront utilisées pour afficher les evenements et les commentaires à l'aide de thymeleaf
        List<Evenement> evenementList= EvenementLibrary.getInstance().listeEvenement();
        context.setVariable("evenementList",evenementList);

        Integer evutList=(Integer) req.getSession().getAttribute("liste");
        context.setVariable("evutList",evutList);




//on  renvoie l'utilisateur vers la page html
        templateEngine.process("PAAfficherPrix", context, resp.getWriter());


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //récupere les données du formulaire et de la session pour afficher les pseudos inscris a un evenement avec la commande listeevut
        String nom_epi = req.getParameter("nom_eve");
        String DateAsString = req.getParameter("dateEvenement");
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateEvenement = LocalDate.parse(DateAsString, dateFormat2);

        int id= Integer.parseInt(EvenementLibrary.getInstance().getId(nom_epi,dateEvenement));

        try {
            Integer liste= EvUtLibrary.getInstance().listeEvUtPrix(id);
            req.getSession().setAttribute("liste",liste);


        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());resp.sendRedirect("/Erreur");
        }resp.sendRedirect("/PAAfficherPrix");
    }
}

