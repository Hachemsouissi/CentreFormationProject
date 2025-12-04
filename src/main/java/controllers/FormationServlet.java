package controllers;

import entities.Formation;
import entities.Professeur;
import services.FormationService;
import services.ProfesseurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/formations")
public class FormationServlet extends HttpServlet {

    private FormationService formationService = new FormationService();
    private ProfesseurService professeurService = new ProfesseurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerFormations(request, response);
                break;
            case "add":
                afficherFormulaireAjout(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "delete":
                supprimerFormation(request, response);
                break;
            default:
                listerFormations(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            ajouterFormation(request, response);
        } else if ("edit".equals(action)) {
            modifierFormation(request, response);
        }
    }

    private void listerFormations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Formation> formations = formationService.lister();
        request.setAttribute("formations", formations);
        request.getRequestDispatcher("/WEB-INF/views/admin/formations.jsp").forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Professeur> professeurs = professeurService.lister();
        request.setAttribute("professeurs", professeurs);
        request.getRequestDispatcher("/WEB-INF/views/admin/formation-form.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Formation formation = formationService.chercher(id);
        List<Professeur> professeurs = professeurService.lister();

        request.setAttribute("formation", formation);
        request.setAttribute("professeurs", professeurs);
        request.getRequestDispatcher("/WEB-INF/views/admin/formation-form.jsp").forward(request, response);
    }

    private void ajouterFormation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String titre = request.getParameter("titre");
            int duree = Integer.parseInt(request.getParameter("duree"));
            int capacite = Integer.parseInt(request.getParameter("capacite"));
            String description = request.getParameter("description");
            int professeurId = Integer.parseInt(request.getParameter("professeurId"));

            Professeur professeur = professeurService.chercher(professeurId);
            Formation formation = new Formation(titre, duree, capacite, description, professeur);

            formationService.ajouter(formation);

            response.sendRedirect(request.getContextPath() + "/admin/formations");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            List<Professeur> professeurs = professeurService.lister();
            request.setAttribute("professeurs", professeurs);
            request.getRequestDispatcher("/WEB-INF/views/admin/formation-form.jsp").forward(request, response);
        }
    }

    private void modifierFormation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titre = request.getParameter("titre");
            int duree = Integer.parseInt(request.getParameter("duree"));
            int capacite = Integer.parseInt(request.getParameter("capacite"));
            String description = request.getParameter("description");
            int professeurId = Integer.parseInt(request.getParameter("professeurId"));

            Formation formation = formationService.chercher(id);
            Professeur professeur = professeurService.chercher(professeurId);

            formation.setTitre(titre);
            formation.setDuree(duree);
            formation.setCapacite(capacite);
            formation.setDescription(description);
            formation.setProfesseur(professeur);

            formationService.modifier(formation);

            response.sendRedirect(request.getContextPath() + "/admin/formations");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            List<Professeur> professeurs = professeurService.lister();
            request.setAttribute("professeurs", professeurs);
            request.getRequestDispatcher("/WEB-INF/views/admin/formation-form.jsp").forward(request, response);
        }
    }

    private void supprimerFormation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            formationService.supprimer(id);
            response.sendRedirect(request.getContextPath() + "/admin/formations");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/formations?error=" + e.getMessage());
        }
    }
}