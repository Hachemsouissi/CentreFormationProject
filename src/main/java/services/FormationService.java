package services;

import java.util.List;
import dao.FormationDAO;
import dao.ProfesseurDAO;
import entities.Formation;
import entities.Professeur;

public class FormationService {

    private FormationDAO formationDAO = new FormationDAO();
    private ProfesseurDAO professeurDAO = new ProfesseurDAO();

    public void ajouter(Formation f) {

        if (f.getTitre() == null || f.getTitre().isEmpty())
            throw new RuntimeException("Titre obligatoire.");

        if (f.getCapacite() <= 0)
            throw new RuntimeException("La capacité doit être > 0.");

        if (f.getProfesseur() != null) {
            Professeur p = professeurDAO.getById(f.getProfesseur().getId());
            if (p == null)
                throw new RuntimeException("Professeur introuvable.");
        }

        formationDAO.add(f);
    }

    public void modifier(Formation f) {
        formationDAO.update(f);
    }

    public void supprimer(int id) {
        formationDAO.delete(id);
    }

    public List<Formation> lister() {
        return formationDAO.getAll();
    }

    public Formation chercher(int id) {
        return formationDAO.getById(id);
    }
}