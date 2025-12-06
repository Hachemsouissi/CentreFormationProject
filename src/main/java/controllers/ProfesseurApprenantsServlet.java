package controllers;

import entities.Formation;
import entities.Inscription;
import entities.User;
import services.FormationService;
import services.InscriptionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/professeur/apprenants")
public class ProfesseurApprenantsServlet extends HttpServlet {

    private FormationService formationService = new FormationService();
    private InscriptionService inscriptionService = new InscriptionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getProfesseur() == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerApprenants(request, response, user);
                break;
            case "details":
                afficherDetailsApprenant(request, response, user);
                break;
            default:
                listerApprenants(request, response, user);
        }
    }

    private void listerApprenants(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {

        // Récupérer toutes les formations du professeur
        List<Formation> mesFormations = formationService.lister().stream()
                .filter(f -> f.getProfesseur() != null &&
                        f.getProfesseur().getId() == user.getProfesseur().getId())
                .collect(Collectors.toList());

        // Récupérer toutes les inscriptions
        List<Inscription> toutesInscriptions = inscriptionService.lister();

        // Créer une map : Formation -> Liste d'inscriptions
        Map<Formation, List<Inscription>> inscriptionsParFormation = new LinkedHashMap<>();

        for (Formation formation : mesFormations) {
            List<Inscription> inscriptions = toutesInscriptions.stream()
                    .filter(i -> i.getFormation() != null &&
                            i.getFormation().getId() == formation.getId())
                    .collect(Collectors.toList());
            inscriptionsParFormation.put(formation, inscriptions);
        }

        // Calculer les statistiques
        int totalApprenants = toutesInscriptions.stream()
                .filter(i -> mesFormations.stream()
                        .anyMatch(f -> f.getId() == i.getFormation().getId()))
                .map(i -> i.getApprenant().getId())
                .collect(Collectors.toSet())
                .size();

        int totalInscriptions = toutesInscriptions.stream()
                .filter(i -> mesFormations.stream()
                        .anyMatch(f -> f.getId() == i.getFormation().getId()))
                .collect(Collectors.toList())
                .size();

        request.setAttribute("inscriptionsParFormation", inscriptionsParFormation);
        request.setAttribute("mesFormations", mesFormations);
        request.setAttribute("totalApprenants", totalApprenants);
        request.setAttribute("totalInscriptions", totalInscriptions);

        request.getRequestDispatcher("/WEB-INF/views/professeur/apprenants.jsp")
                .forward(request, response);
    }

    private void afficherDetailsApprenant(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {

        int apprenantId = Integer.parseInt(request.getParameter("id"));

        // Récupérer les formations du professeur
        List<Formation> mesFormations = formationService.lister().stream()
                .filter(f -> f.getProfesseur() != null &&
                        f.getProfesseur().getId() == user.getProfesseur().getId())
                .collect(Collectors.toList());

        // Récupérer les inscriptions de cet apprenant dans les formations du professeur
        List<Inscription> inscriptionsApprenant = inscriptionService.lister().stream()
                .filter(i -> i.getApprenant() != null &&
                        i.getApprenant().getId() == apprenantId &&
                        mesFormations.stream().anyMatch(f -> f.getId() == i.getFormation().getId()))
                .collect(Collectors.toList());

        if (inscriptionsApprenant.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/professeur/apprenants");
            return;
        }

        request.setAttribute("inscriptionsApprenant", inscriptionsApprenant);
        request.setAttribute("apprenant", inscriptionsApprenant.get(0).getApprenant());

        request.getRequestDispatcher("/WEB-INF/views/professeur/apprenant-details.jsp")
                .forward(request, response);
    }
}