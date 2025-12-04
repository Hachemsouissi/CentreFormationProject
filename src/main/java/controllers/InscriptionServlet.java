package controllers;

import entities.Inscription;
import entities.Apprenant;
import entities.Formation;
import services.InscriptionService;
import services.ApprenantService;
import services.FormationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/inscriptions")
public class InscriptionServlet extends HttpServlet {

    private InscriptionService inscriptionService = new InscriptionService();
    private ApprenantService apprenantService = new ApprenantService();
    private FormationService formationService = new FormationService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerInscriptions(request, response);
                break;
            case "add":
                afficherFormulaireAjout(request, response);
                break;
            case "delete":
                supprimerInscription(request, response);
                break;
            default:
                listerInscriptions(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            ajouterInscription(request, response);
        }
    }

    private void listerInscriptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Inscription> inscriptions = inscriptionService.lister();
        request.setAttribute("inscriptions", inscriptions);
        request.getRequestDispatcher("/WEB-INF/views/admin/inscriptions.jsp").forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Apprenant> apprenants = apprenantService.lister();
        List<Formation> formations = formationService.lister();

        request.setAttribute("apprenants", apprenants);
        request.setAttribute("formations", formations);
        request.getRequestDispatcher("/WEB-INF/views/admin/inscription-form.jsp").forward(request, response);
    }

    private void ajouterInscription(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));
            int idFormation = Integer.parseInt(request.getParameter("idFormation"));

            inscriptionService.inscrire(idApprenant, idFormation);

            response.sendRedirect(request.getContextPath() + "/admin/inscriptions");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            List<Apprenant> apprenants = apprenantService.lister();
            List<Formation> formations = formationService.lister();
            request.setAttribute("apprenants", apprenants);
            request.setAttribute("formations", formations);
            request.getRequestDispatcher("/WEB-INF/views/admin/inscription-form.jsp").forward(request, response);
        }
    }

    private void supprimerInscription(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            inscriptionService.supprimer(id);
            response.sendRedirect(request.getContextPath() + "/admin/inscriptions");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/inscriptions?error=" + e.getMessage());
        }
    }
}