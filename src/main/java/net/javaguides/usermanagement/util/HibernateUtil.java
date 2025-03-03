package net.javaguides.usermanagement.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import net.javaguides.usermanagement.model.User;
import org.hibernate.cfg.*;
import org.hibernate.service.*;



public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory==null) {
			try {
				Configuration configuration =new Configuration();
				
				Properties settings=new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); // Nouvelle version du driver
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC");
				settings.put(Environment.USER, "root");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				// Choisis "update" ou "create-drop"
				settings.put(Environment.HBM2DDL_AUTO, "validate"); 
				
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(User.class);
				
				ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate service Registry created");
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;
			} catch (Exception e) {
				System.out.println("Erreur Hibernate: " + e.getMessage());
			    throw new RuntimeException("Erreur de configuration Hibernate", e);
			}
		}
		return sessionFactory;
	}

}
