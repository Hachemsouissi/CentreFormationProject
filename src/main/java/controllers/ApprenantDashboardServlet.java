package controllers;

import entities.Inscription;
import entities.User;
import services.InscriptionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/apprenant/dashboard")
public class ApprenantDashboardServlet extends HttpServlet {

    private InscriptionService inscriptionService = new InscriptionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getApprenant() == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Récupérer les inscriptions de l'apprenant
        List<Inscription> toutesInscriptions = inscriptionService.lister();
        List<Inscription> mesInscriptions = toutesInscriptions.stream()
                .filter(i -> i.getApprenant() != null &&
                        i.getApprenant().getId() == user.getApprenant().getId())
                .collect(Collectors.toList());

        request.setAttribute("mesInscriptions", mesInscriptions);
        request.setAttribute("totalInscriptions", mesInscriptions.size());

        request.getRequestDispatcher("/WEB-INF/views/apprenant/dashboard.jsp")
                .forward(request, response);
    }
}