package controllers;

import entities.Cours;
import entities.Inscription;
import entities.User;
import services.CoursService;
import services.InscriptionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/apprenant/cours")
public class ApprenantCoursServlet extends HttpServlet {

    private CoursService coursService = new CoursService();
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

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                listerMesCours(request, response, user);
                break;
            case "view":
                afficherCours(request, response, user);
                break;
            default:
                listerMesCours(request, response, user);
        }
    }

    private void listerMesCours(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {

        List<Inscription> mesInscriptions = inscriptionService.lister().stream()
                .filter(i -> i.getApprenant() != null &&
                        i.getApprenant().getId() == user.getApprenant().getId())
                .toList();

        List<Cours> mesCours = new ArrayList<>();
        for (Inscription inscription : mesInscriptions) {
            List<Cours> coursFormation = coursService.lister().stream()
                    .filter(c -> c.getFormation().getId() == inscription.getFormation().getId()
                            && c.isVisible())
                    .toList();
            mesCours.addAll(coursFormation);
        }

        request.setAttribute("mesCours", mesCours);
        request.setAttribute("mesInscriptions", mesInscriptions);
        request.getRequestDispatcher("/WEB-INF/views/apprenant/cours-list.jsp")
                .forward(request, response);
    }

    private void afficherCours(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cours cours = coursService.chercher(id);


        List<Inscription> mesInscriptions = inscriptionService.lister().stream()
                .filter(i -> i.getApprenant() != null &&
                        i.getApprenant().getId() == user.getApprenant().getId() &&
                        i.getFormation().getId() == cours.getFormation().getId())
                .toList();

        if (mesInscriptions.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/apprenant/cours");
            return;
        }

        request.setAttribute("cours", cours);
        request.getRequestDispatcher("/WEB-INF/views/apprenant/cours-view.jsp")
                .forward(request, response);
    }
}