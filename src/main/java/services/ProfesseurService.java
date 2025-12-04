package services;

import java.util.List;
import dao.ProfesseurDAO;
import entities.Professeur;

public class ProfesseurService {

    private ProfesseurDAO professeurDAO = new ProfesseurDAO();

    public void ajouter(Professeur p) {
        if (p.getNom() == null || p.getNom().isEmpty())
            throw new RuntimeException("Le nom du professeur est obligatoire.");

        professeurDAO.add(p);
    }

    public void modifier(Professeur p) {
        professeurDAO.update(p);
    }

    public void supprimer(int id) {
        professeurDAO.delete(id);
    }

    public List<Professeur> lister() {
        return professeurDAO.getAll();
    }

    public Professeur chercher(int id) {
        return professeurDAO.getById(id);
    }
}
