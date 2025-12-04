package services;

import java.time.LocalDate;
import java.util.List;
import dao.InscriptionDAO;
import dao.FormationDAO;
import dao.ApprenantDAO;
import entities.Inscription;
import entities.Formation;
import entities.Apprenant;

public class InscriptionService {

    private InscriptionDAO inscriptionDAO = new InscriptionDAO();
    private FormationDAO formationDAO = new FormationDAO();
    private ApprenantDAO apprenantDAO = new ApprenantDAO();

    public void inscrire(int idApprenant, int idFormation) {

        Apprenant a = apprenantDAO.getById(idApprenant);
        if (a == null)
            throw new RuntimeException("Apprenant introuvable.");

        Formation f = formationDAO.getById(idFormation);
        if (f == null)
            throw new RuntimeException("Formation introuvable.");

        int inscrits = inscriptionDAO.countByFormation(idFormation);
        if (inscrits >= f.getCapacite())
            throw new RuntimeException("Plus de places disponibles dans cette formation.");

        boolean dejaInscrit = inscriptionDAO.exists(idApprenant, idFormation);
        if (dejaInscrit)
            throw new RuntimeException("Cet apprenant est déjà inscrit dans cette formation.");

        Inscription ins = new Inscription();
        ins.setApprenant(a);
        ins.setFormation(f);
        ins.setDateInscription(LocalDate.now().toString());

        inscriptionDAO.add(ins);
    }

    public List<Inscription> lister() {
        return inscriptionDAO.getAll();
    }

    public void supprimer(int id) {
        inscriptionDAO.delete(id);
    }
}