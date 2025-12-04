package controllers;

import entities.Apprenant;
import services.ApprenantService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/apprenants")
public class ApprenantServlet extends HttpServlet {

    private ApprenantService apprenantService = new ApprenantService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerApprenants(request, response);
                break;
            case "add":
                afficherFormulaireAjout(request, response);
                break;
            case "edit":
                afficherFormulaireModification(request, response);
                break;
            case "delete":
                supprimerApprenant(request, response);
                break;
            default:
                listerApprenants(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            ajouterApprenant(request, response);
        } else if ("edit".equals(action)) {
            modifierApprenant(request, response);
        }
    }

    private void listerApprenants(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Apprenant> apprenants = apprenantService.lister();
        request.setAttribute("apprenants", apprenants);
        request.getRequestDispatcher("/WEB-INF/views/admin/apprenants.jsp").forward(request, response);
    }

    private void afficherFormulaireAjout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/apprenant-form.jsp").forward(request, response);
    }

    private void afficherFormulaireModification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Apprenant apprenant = apprenantService.chercher(id);
        request.setAttribute("apprenant", apprenant);
        request.getRequestDispatcher("/WEB-INF/views/admin/apprenant-form.jsp").forward(request, response);
    }

    private void ajouterApprenant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");

            Apprenant apprenant = new Apprenant(nom, prenom, email, telephone);
            apprenantService.ajouter(apprenant);

            response.sendRedirect(request.getContextPath() + "/admin/apprenants");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/apprenant-form.jsp").forward(request, response);
        }
    }

    private void modifierApprenant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");

            Apprenant apprenant = apprenantService.chercher(id);
            apprenant.setNom(nom);
            apprenant.setPrenom(prenom);
            apprenant.setEmail(email);
            apprenant.setTelephone(telephone);

            apprenantService.modifier(apprenant);

            response.sendRedirect(request.getContextPath() + "/admin/apprenants");
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/admin/apprenant-form.jsp").forward(request, response);
        }
    }

    private void supprimerApprenant(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            apprenantService.supprimer(id);
            response.sendRedirect(request.getContextPath() + "/admin/apprenants");
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/admin/apprenants?error=" + e.getMessage());
        }
    }
}