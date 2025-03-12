package net.javaguides.usermanagement.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.javaguides.usermanagement.model.Medecin;
import net.javaguides.usermanagement.util.HibernateUtil;

public class MedecinDAO {

    /**
     * Génère le nouveau code médecin au format "MXXX".
     * Par exemple, si le dernier code est "M005", le nouveau sera "M006".
     */
    public String generateMedecinCode() {
        String newCode = "M001"; // valeur par défaut si aucun médecin n'existe
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // La requête trie par la partie numérique du code (en supposant le format "MXXX")
            List<Medecin> list = session.createQuery(
                    "FROM Medecin m ORDER BY cast(substring(m.codemed, 2) as integer) DESC", Medecin.class)
                    .setMaxResults(1)
                    .getResultList();
            if (!list.isEmpty()) {
                Medecin latest = list.get(0);
                String code = latest.getCodemed(); // ex: "M005"
                int num = Integer.parseInt(code.substring(1)); // récupère 5
                newCode = "M" + String.format("%03d", num + 1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return newCode;
    }
    
    public void saveMedecin(Medecin medecin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            // Génération du nouveau code et affectation à l'objet
            String newCode = generateMedecinCode();
            medecin.setCodemed(newCode);
            session.save(medecin);
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateMedecin(Medecin medecin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(medecin);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteMedecin(String codemed) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Medecin medecin = session.get(Medecin.class, codemed);
            if (medecin != null) {
                session.delete(medecin);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Medecin getMedecin(String codemed) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Medecin.class, codemed);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Medecin> getAllMedecins() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Medecin", Medecin.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
