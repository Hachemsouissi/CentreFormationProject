package controllers;

import services.ApprenantService;
import services.FormationService;
import services.InscriptionService;
import services.ProfesseurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class DashboardServlet extends HttpServlet {

    private ApprenantService apprenantService = new ApprenantService();
    private FormationService formationService = new FormationService();
    private InscriptionService inscriptionService = new InscriptionService();
    private ProfesseurService professeurService = new ProfesseurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Compter les statistiques
        int totalApprenants = apprenantService.lister().size();
        int totalFormations = formationService.lister().size();
        int totalInscriptions = inscriptionService.lister().size();
        int totalProfesseurs = professeurService.lister().size();

        // Passer les données à la JSP
        request.setAttribute("totalApprenants", totalApprenants);
        request.setAttribute("totalFormations", totalFormations);
        request.setAttribute("totalInscriptions", totalInscriptions);
        request.setAttribute("totalProfesseurs", totalProfesseurs);

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }
}