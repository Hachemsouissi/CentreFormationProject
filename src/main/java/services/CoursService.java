package services;

import dao.CoursDAO;
import dao.FormationDAO;
import dao.ProfesseurDAO;
import entities.Cours;
import entities.Formation;
import entities.Professeur;

import java.util.List;

public class CoursService {

    private CoursDAO coursDAO = new CoursDAO();
    private FormationDAO formationDAO = new FormationDAO();
    private ProfesseurDAO professeurDAO = new ProfesseurDAO();

    public void ajouter(Cours cours) {
        if (cours.getTitre() == null || cours.getTitre().isEmpty())
            throw new RuntimeException("Le titre du cours est obligatoire.");

        if (cours.getContenu() == null || cours.getContenu().isEmpty())
            throw new RuntimeException("Le contenu du cours est obligatoire.");

        if (cours.getFormation() != null) {
            Formation f = formationDAO.getById(cours.getFormation().getId());
            if (f == null)
                throw new RuntimeException("Formation introuvable.");
        }

        if (cours.getProfesseur() != null) {
            Professeur p = professeurDAO.getById(cours.getProfesseur().getId());
            if (p == null)
                throw new RuntimeException("Professeur introuvable.");
        }

        coursDAO.add(cours);
    }

    public void modifier(Cours cours) {
        coursDAO.update(cours);
    }

    public void supprimer(int id) {
        coursDAO.delete(id);
    }

    public List<Cours> lister() {
        return coursDAO.getAll();
    }

    public Cours chercher(int id) {
        return coursDAO.getById(id);
    }

    public List<Cours> listerParFormation(int formationId) {
        return coursDAO.getByFormation(formationId);
    }

    public List<Cours> listerParProfesseur(int professeurId) {
        return coursDAO.getByProfesseur(professeurId);
    }
    public List<Cours> listerCoursVisiblesParFormation(int formationId) {
        return coursDAO.getVisibleByFormation(formationId);
    }

    public void changerVisibilite(int coursId, boolean visible) {
        Cours cours = coursDAO.getById(coursId);
        if (cours != null) {
            cours.setVisible(visible);
            coursDAO.update(cours);
        }
    }
}