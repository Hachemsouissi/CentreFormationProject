package controllers;

import entities.Cours;
import entities.Formation;
import entities.User;
import services.CoursService;
import services.FormationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/professeur/cours")
public class ProfesseurCoursServlet extends HttpServlet {

    private CoursService coursService = new CoursService();
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

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerCours(request, response, user);
                break;
            case "add":
                afficherFormulaireAjout(request, response, user);
                break;
            case "edit":
                afficherFormulaireModification(request, response, user);
                break;
            case "delete":
                supprimerCours(request, response, user);
                break;
            case "view":
                afficherCours(request, response);
                break;
            default:
                listerCours(request, response, user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getProfesseur() == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            ajouterCours(request, response, user);
        } else if ("edit".equals(action)) {
            modifierCours(request, response, user);
        }
    }

    private void listerCours(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        List<Cours> mesCours = coursService.listerParProfesseur(user.getProfesseur().getId());
        request.setAttribute("mesCours", mesCours);
        request.getRequestDispatcher("/WEB-INF/views/professeur/cours-list.jsp")
                .forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        // Récupérer seulement les formations du professeur
        List<Formation> toutesFormations = formationService.lister();
        List<Formation> mesFormations = toutesFormations.stream()
                .filter(f -> f.getProfesseur() != null &&
                        f.getProfesseur().getId() == user.getProfesseur().getId())
                .collect(Collectors.toList());

        request.setAttribute("mesFormations", mesFormations);
        request.getRequestDispatcher("/WEB-INF/views/professeur/cours-form.jsp")
                .forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cours cours = coursService.chercher(id);

        // Vérifier que le cours appartient bien au professeur
        if (cours.getProfesseur().getId() != user.getProfesseur().getId()) {
            response.sendRedirect(request.getContextPath() + "/professeur/cours");
            return;
        }

        List<Formation> toutesFormations = formationService.lister();
        List<Formation> mesFormations = toutesFormations.stream()
                .filter(f -> f.getProfesseur() != null &&
                        f.getProfesseur().getId() == user.getProfesseur().getId())
                .collect(Collectors.toList());

        request.setAttribute("cours", cours);
        request.setAttribute("mesFormations", mesFormations);
        request.getRequestDispatcher("/WEB-INF/views/professeur/cours-form.jsp")
                .forward(request, response);
    }

    private void ajouterCours(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            String titre = request.getParameter("titre");
            String contenu = request.getParameter("contenu");
            int formationId = Integer.parseInt(request.getParameter("formationId"));

            Formation formation = formationService.chercher(formationId);

            // Vérifier que la formation appartient au professeur
            if (formation.getProfesseur().getId() != user.getProfesseur().getId()) {
                throw new RuntimeException("Vous ne pouvez pas ajouter de cours à cette formation.");
            }

            Cours cours = new Cours(titre, contenu, LocalDate.now().toString(),
                    formation, user.getProfesseur());

            coursService.ajouter(cours);

            response.sendRedirect(request.getContextPath() + "/professeur/cours");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            afficherFormulaireAjout(request, response, user);
        }
    }

    private void modifierCours(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titre = request.getParameter("titre");
            String contenu = request.getParameter("contenu");
            int formationId = Integer.parseInt(request.getParameter("formationId"));

            Cours cours = coursService.chercher(id);

            // Vérifier que le cours appartient au professeur
            if (cours.getProfesseur().getId() != user.getProfesseur().getId()) {
                throw new RuntimeException("Vous ne pouvez pas modifier ce cours.");
            }

            Formation formation = formationService.chercher(formationId);

            cours.setTitre(titre);
            cours.setContenu(contenu);
            cours.setFormation(formation);

            coursService.modifier(cours);

            response.sendRedirect(request.getContextPath() + "/professeur/cours");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            afficherFormulaireModification(request, response, user);
        }
    }

    private void supprimerCours(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Cours cours = coursService.chercher(id);

            // Vérifier que le cours appartient au professeur
            if (cours.getProfesseur().getId() != user.getProfesseur().getId()) {
                throw new RuntimeException("Vous ne pouvez pas supprimer ce cours.");
            }

            coursService.supprimer(id);
            response.sendRedirect(request.getContextPath() + "/professeur/cours");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/professeur/cours?error=" + e.getMessage());
        }
    }

    private void afficherCours(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cours cours = coursService.chercher(id);
        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/WEB-INF/views/professeur/cours-view.jsp")
                .forward(request, response);
    }
}