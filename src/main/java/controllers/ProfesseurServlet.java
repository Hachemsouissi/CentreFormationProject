package controllers;

import entities.Professeur;
import services.ProfesseurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/professeurs")
public class ProfesseurServlet extends HttpServlet {

    private ProfesseurService professeurService = new ProfesseurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerProfesseurs(request, response);
                break;
            case "add":
                afficherFormulaireAjout(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "delete":
                supprimerProfesseur(request, response);
                break;
            default:
                listerProfesseurs(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            ajouterProfesseur(request, response);
        } else if ("edit".equals(action)) {
            modifierProfesseur(request, response);
        }
    }

    private void listerProfesseurs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Professeur> professeurs = professeurService.lister();
        request.setAttribute("professeurs", professeurs);
        request.getRequestDispatcher("/WEB-INF/views/admin/professeurs.jsp").forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/professeur-form.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Professeur professeur = professeurService.chercher(id);
        request.setAttribute("professeur", professeur);
        request.getRequestDispatcher("/WEB-INF/views/admin/professeur-form.jsp").forward(request, response);
    }

    private void ajouterProfesseur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String specialite = request.getParameter("specialite");

            Professeur professeur = new Professeur(nom, prenom, email, specialite);
            professeurService.ajouter(professeur);

            response.sendRedirect(request.getContextPath() + "/admin/professeurs");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/professeur-form.jsp").forward(request, response);
        }
    }

    private void modifierProfesseur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String specialite = request.getParameter("specialite");

            Professeur professeur = professeurService.chercher(id);
            professeur.setNom(nom);
            professeur.setPrenom(prenom);
            professeur.setEmail(email);
            professeur.setSpecialite(specialite);

            professeurService.modifier(professeur);

            response.sendRedirect(request.getContextPath() + "/admin/professeurs");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/professeur-form.jsp").forward(request, response);
        }
    }

    private void supprimerProfesseur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            professeurService.supprimer(id);
            response.sendRedirect(request.getContextPath() + "/admin/professeurs");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/professeurs?error=" + e.getMessage());
        }
    }
}