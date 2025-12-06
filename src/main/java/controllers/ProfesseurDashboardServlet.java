package controllers;

import entities.Formation;
import entities.User;
import services.FormationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/professeur/dashboard")
public class ProfesseurDashboardServlet extends HttpServlet {

    private FormationService formationService = new FormationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getProfesseur() == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Récupérer les formations du professeur
        List<Formation> toutesFormations = formationService.lister();
        List<Formation> mesFormations = toutesFormations.stream()
                .filter(f -> f.getProfesseur() != null &&
                        f.getProfesseur().getId() == user.getProfesseur().getId())
                .collect(Collectors.toList());

        request.setAttribute("mesFormations", mesFormations);
        request.setAttribute("totalFormations", mesFormations.size());

        request.getRequestDispatcher("/WEB-INF/views/professeur/dashboard.jsp")
                .forward(request, response);
    }
}