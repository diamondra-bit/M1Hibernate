package net.javaguides.usermanagement.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import net.javaguides.usermanagement.model.Patient;
import net.javaguides.usermanagement.model.Visiter;
import net.javaguides.usermanagement.model.Medecin;
import org.hibernate.cfg.Environment;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver"); // Nouvelle version du driver
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/demo?useSSL=false&serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, ""); // Ajoute le mot de passe si nécessaire
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                // Choisis "update" pour modifier la structure sans supprimer les données existantes
                settings.put(Environment.HBM2DDL_AUTO, "update"); 

                configuration.setProperties(settings);
                
                // Ajout des classes annotées pour Hibernate
                configuration.addAnnotatedClass(Patient.class);
                configuration.addAnnotatedClass(Medecin.class);
                configuration.addAnnotatedClass(Visiter.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Service Registry created");

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Erreur Hibernate: " + e.getMessage());
                throw new RuntimeException("Erreur de configuration Hibernate", e);
            }
        }
        return sessionFactory;
    }
}
