package net.javaguides.usermanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.javaguides.usermanagement.model.User;
import net.javaguides.usermanagement.util.HibernateUtil;


public class UserDAO {
	public void saveUser(User user) {
		Transaction transaction=null;
		
		try(Session session =HibernateUtil.getSessionFactory().openSession()){
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		Transaction transaction=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			session.update(user);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id) {
	    Transaction transaction = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        User user = session.get(User.class, id);
	        if (user != null) {
	            session.delete(user);
	            System.out.println("User deleted: " + id);
	        }
	        transaction.commit(); // Ajout de cette ligne
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	}

	
	public User getUser(int id) {
		Transaction transaction=null;
		User user=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
			user=session.get(User.class, id);
			transaction.commit();
		}catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<User> getAllUser() {
	    Transaction transaction = null;
	    List<User> listOfUser = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        listOfUser = session.createQuery("from User", User.class).getResultList();
	        transaction.commit();
	        System.out.println("Nombre d'utilisateurs récupérés : " + listOfUser.size());
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return listOfUser;
	}
}
