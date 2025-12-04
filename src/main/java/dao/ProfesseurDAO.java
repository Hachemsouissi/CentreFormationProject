package dao;
import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class ProfesseurDAO {

    public void add(Professeur p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    public void update(Professeur p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    public void delete(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Professeur p = session.get(Professeur.class, id);
            if (p != null) session.remove(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    public Professeur getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Professeur.class, id);
        }
    }

    public List<Professeur> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Professeur", Professeur.class).list();
        }
    }
}
