package controllers;

import entities.Apprenant;
import entities.Professeur;
import entities.User;
import services.ApprenantService;
import services.ProfesseurService;
import services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUsersServlet extends HttpServlet {

    private UserService userService = new UserService();
    private ApprenantService apprenantService = new ApprenantService();
    private ProfesseurService professeurService = new ProfesseurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerUtilisateurs(request, response);
                break;
            case "add":
                afficherFormulaireAjout(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "delete":
                supprimerUtilisateur(request, response);
                break;
            default:
                listerUtilisateurs(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            ajouterUtilisateur(request, response);
        } else if ("edit".equals(action)) {
            modifierUtilisateur(request, response);
        }
    }

    private void listerUtilisateurs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userService.lister();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp")
                .forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Apprenant> apprenants = apprenantService.lister();
        List<Professeur> professeurs = professeurService.lister();

        request.setAttribute("apprenants", apprenants);
        request.setAttribute("professeurs", professeurs);
        request.getRequestDispatcher("/WEB-INF/views/admin/user-form.jsp")
                .forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.chercher(id);

        List<Apprenant> apprenants = apprenantService.lister();
        List<Professeur> professeurs = professeurService.lister();

        request.setAttribute("user", user);
        request.setAttribute("apprenants", apprenants);
        request.setAttribute("professeurs", professeurs);
        request.getRequestDispatcher("/WEB-INF/views/admin/user-form.jsp")
                .forward(request, response);
    }

    private void ajouterUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");

            User user = new User(username, password, role);

            // Associer l'apprenant ou le professeur selon le rôle
            if ("apprenant".equals(role)) {
                String apprenantIdStr = request.getParameter("apprenantId");
                if (apprenantIdStr != null && !apprenantIdStr.isEmpty()) {
                    int apprenantId = Integer.parseInt(apprenantIdStr);
                    Apprenant apprenant = apprenantService.chercher(apprenantId);
                    user.setApprenant(apprenant);
                }
            } else if ("professeur".equals(role)) {
                String professeurIdStr = request.getParameter("professeurId");
                if (professeurIdStr != null && !professeurIdStr.isEmpty()) {
                    int professeurId = Integer.parseInt(professeurIdStr);
                    Professeur professeur = professeurService.chercher(professeurId);
                    user.setProfesseur(professeur);
                }
            }

            userService.creerUtilisateur(user);

            response.sendRedirect(request.getContextPath() + "/admin/users");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            afficherFormulaireAjout(request, response);
        }
    }

    private void modifierUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String role = request.getParameter("role");

            User user = userService.chercher(id);
            user.setUsername(username);
            user.setRole(role);

            // Mise à jour du mot de passe si fourni
            String newPassword = request.getParameter("newPassword");
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }

            // Associer l'apprenant ou le professeur selon le rôle
            if ("apprenant".equals(role)) {
                String apprenantIdStr = request.getParameter("apprenantId");
                if (apprenantIdStr != null && !apprenantIdStr.isEmpty()) {
                    int apprenantId = Integer.parseInt(apprenantIdStr);
                    Apprenant apprenant = apprenantService.chercher(apprenantId);
                    user.setApprenant(apprenant);
                    user.setProfesseur(null);
                }
            } else if ("professeur".equals(role)) {
                String professeurIdStr = request.getParameter("professeurId");
                if (professeurIdStr != null && !professeurIdStr.isEmpty()) {
                    int professeurId = Integer.parseInt(professeurIdStr);
                    Professeur professeur = professeurService.chercher(professeurId);
                    user.setProfesseur(professeur);
                    user.setApprenant(null);
                }
            } else {
                user.setApprenant(null);
                user.setProfesseur(null);
            }

            userService.modifier(user);

            response.sendRedirect(request.getContextPath() + "/admin/users");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            afficherFormulaireModification(request, response);
        }
    }

    private void supprimerUtilisateur(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            userService.supprimer(id);
            response.sendRedirect(request.getContextPath() + "/admin/users");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/users?error=" + e.getMessage());
        }
    }
}