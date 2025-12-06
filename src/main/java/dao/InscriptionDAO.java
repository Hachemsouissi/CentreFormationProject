package dao;

import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class InscriptionDAO {

    public void add(Inscription i) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(i);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void update(Inscription i) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(i);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Inscription i = session.get(Inscription.class, id);
            if (i != null) session.remove(i);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public Inscription getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Inscription.class, id);
        }
    }

    public List<Inscription> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Inscription", Inscription.class).list();
        }
    }

    // CORRECTION: Utiliser les objets au lieu des IDs
    public boolean exists(int idApprenant, int idFormation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                            "select count(i.id) from Inscription i where i.apprenant.id = :idApprenant and i.formation.id = :idFormation", Long.class)
                    .setParameter("idApprenant", idApprenant)
                    .setParameter("idFormation", idFormation)
                    .uniqueResult();
            return count != null && count > 0;
        }
    }

    public int countByFormation(int idFormation) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long count = session.createQuery(
                            "select count(i.id) from Inscription i where i.formation.id = :idFormation", Long.class)
                    .setParameter("idFormation", idFormation)
                    .uniqueResult();
            return count != null ? count.intValue() : 0;
        }
    }
}