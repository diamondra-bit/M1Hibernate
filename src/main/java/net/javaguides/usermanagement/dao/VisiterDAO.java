package net.javaguides.usermanagement.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.javaguides.usermanagement.model.Visiter;
import net.javaguides.usermanagement.util.HibernateUtil;

public class VisiterDAO {

	public void saveVisiter(Visiter visiter) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()){
	        transaction = session.beginTransaction();
	        session.save(visiter);
	        transaction.commit();
	    } catch(Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}


    public void updateVisiter(Visiter visiter) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(visiter);
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteVisiter(int codevisiter) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Visiter visiter = session.get(Visiter.class, codevisiter);
            if (visiter != null) {
                session.delete(visiter);
            }
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Visiter getVisiter(int codevisiter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Visiter.class, codevisiter);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Visiter> getAllVisiters() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Visiter", Visiter.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
