package net.javaguides.usermanagement.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.javaguides.usermanagement.model.Patient;
import net.javaguides.usermanagement.util.HibernateUtil;

public class PatientDAO {

    /**
     * Génère le nouveau code patient au format "PXXX".
     * Par exemple, si le dernier code est "P005", le nouveau sera "P006".
     */
    public String generatePatientCode() {
        String newCode = "P001"; // valeur par défaut si aucun patient n'existe
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            // Récupère le dernier patient en triant par code (attention : 
            // cette requête suppose que les codes respectent le format "PXXX")
            List<Patient> list = session.createQuery(
                    "FROM Patient p ORDER BY cast(substring(p.codepal, 2) as integer) DESC", Patient.class)
                    .setMaxResults(1)
                    .getResultList();
            if (!list.isEmpty()) {
                Patient latest = list.get(0);
                String code = latest.getCodepal(); // ex: "P005"
                int num = Integer.parseInt(code.substring(1)); // récupère 5
                newCode = "P" + String.format("%03d", num + 1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return newCode;
    }

    public void savePatient(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            // Génération et affectation du nouveau code patient
            String newCode = generatePatientCode();
            patient.setCodepal(newCode);
            session.save(patient);
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updatePatient(Patient patient) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePatient(String codepal) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, codepal);
            if (patient != null) {
                session.delete(patient);
            }
            transaction.commit();
        } catch(Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Patient getPatient(String codepal) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.get(Patient.class, codepal);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Patient> getAllPatients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Patient", Patient.class).getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Patient> searchPatients(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "FROM Patient WHERE LOWER(nom) LIKE :keyword OR codepal LIKE :keyword";
            return session.createQuery(hql, Patient.class)
                    .setParameter("keyword", "%" + keyword.toLowerCase() + "%")
                    .getResultList();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
