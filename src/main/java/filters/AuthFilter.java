package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/professeur/*", "/apprenant/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        String loginURI = req.getContextPath() + "/login";

        boolean isLoginRequest = req.getRequestURI().equals(loginURI);
        boolean isLoginPage = req.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn || isLoginRequest || isLoginPage) {
            // Vérifier le rôle pour les URLs spécifiques
            if (isLoggedIn) {
                String role = (String) session.getAttribute("role");
                String uri = req.getRequestURI();

                if (uri.contains("/admin/") && !"admin".equals(role)) {
                    res.sendRedirect(req.getContextPath() + "/login");
                    return;
                }
                if (uri.contains("/professeur/") && !"professeur".equals(role)) {
                    res.sendRedirect(req.getContextPath() + "/login");
                    return;
                }
                if (uri.contains("/apprenant/") && !"apprenant".equals(role)) {
                    res.sendRedirect(req.getContextPath() + "/login");
                    return;
                }
            }
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURI);
        }
    }
}