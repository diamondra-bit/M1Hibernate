package net.javaguides.usermanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity 
@Table(name = "medecin")
public class Medecin implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Codemed devient une chaîne, sans auto-incrément de la base
    @Id
    @Column(name = "codemed", length = 10, nullable = false)
    private String codemed;
    
    @Column(name = "nom", nullable = false)
    private String nom;
    
    @Column(name = "prenom", nullable = false)
    private String prenom;
    
    @Column(name = "grade", nullable = false)
    private String grade;

    // Constructeur par défaut obligatoire pour Hibernate
    public Medecin() {}

    // Constructeur sans code, celui-ci sera généré automatiquement
    public Medecin(String nom, String prenom, String grade) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }
    
    // Constructeur complet (pour édition par exemple)
    public Medecin(String codemed, String nom, String prenom, String grade) {
        this.codemed = codemed;
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    // Getters et Setters
    public String getCodemed() {
        return codemed;
    }

    public void setCodemed(String codemed) {
        this.codemed = codemed;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
