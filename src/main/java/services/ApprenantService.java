package services;

import java.util.List;
import dao.ApprenantDAO;
import entities.Apprenant;

public class ApprenantService {

    private ApprenantDAO ApprenantDAO = new ApprenantDAO();

    // Ajouter un stagiaire
    public void ajouter(Apprenant s) {
        if (s.getNom() == null || s.getNom().isEmpty())
            throw new RuntimeException("Le nom du stagiaire est obligatoire.");

        ApprenantDAO.add(s);
    }

    // Modifier un stagiaire
    public void modifier(Apprenant s) {
        ApprenantDAO.update(s);
    }

    // Supprimer un stagiaire
    public void supprimer(int id) {
        ApprenantDAO.delete(id);
    }

    // Lister les stagiaires
    public List<Apprenant> lister() {
        return ApprenantDAO.getAll();
    }

    // Chercher par ID
    public Apprenant chercher(int id) {
        return ApprenantDAO.getById(id);
    }
}
