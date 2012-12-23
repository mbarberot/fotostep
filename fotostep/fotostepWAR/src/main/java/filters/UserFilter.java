package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *   Empêche l'accès aux pages utilisateur pour un visiteur non loggé
 *   @author Joan Racenet
 * */
public class UserFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (((HttpServletRequest) servletRequest).getSession().getAttribute("userId") == null) {
            // Not logged in, so redirect request to login page.
            ((HttpServletResponse) servletResponse).sendRedirect("/fotostep/register.jsf");
        } else {
            // Logged in, so just continue request.
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }
}
