package filtres;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authentification implements Filter{
//filtre permettant de devoir se connecter pour s'inscrire ou poster des commentaires
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httplRequest=(HttpServletRequest) request;
            String identifiant=(String) httplRequest.getSession().getAttribute("pseudo");
            if(identifiant==null || "".equals(identifiant)){
                System.out.println("Il faut être connecté pour acceder à cette page");
                HttpServletResponse httpResponse=(HttpServletResponse) response;
                httpResponse.sendRedirect("../Connexion");
                return;
            }
            chain.doFilter(request,response);
        }

        @Override
        public void destroy() {

        }
    }

