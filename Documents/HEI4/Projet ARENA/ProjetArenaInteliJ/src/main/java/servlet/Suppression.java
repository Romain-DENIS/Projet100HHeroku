package servlet;


import managers.UtilisateurLibrary;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Suppression")
public class Suppression  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pseudo= (String) req.getSession().getAttribute("pseudo");
        try {

            UtilisateurLibrary.getInstance().deleteUtilisateur(pseudo);


        } catch (IllegalArgumentException e) {
            req.getSession().setAttribute("addQuoteErrorMessage", e.getMessage());resp.sendRedirect("/Erreur");
        }
        req.getSession().removeAttribute("pseudo");
        resp.sendRedirect("Connexion");
    }
}
