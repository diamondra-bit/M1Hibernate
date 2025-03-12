package net.javaguides.usermanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity 
@Table(name = "medecin")
public class Medecin implements Serializable {
    
    private static final long serialVersionUID = 1L;  // UID pour éviter les problèmes de sérialisation
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codemed")
    private Integer codemed;  // ⚠️ `Integer` au lieu de `int` pour éviter les erreurs Hibernate

    @Column(name = "nom", nullable = false)
    private String nom;  // ⚠️ Respecte les conventions camelCase

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "grade", nullable = false)
    private String grade;

    // 🔹 Constructeur par défaut obligatoire pour Hibernate
    public Medecin() {}

    // 🔹 Constructeur sans id (Hibernate gère l'id auto)
    public Medecin(String nom, String prenom, String grade) {
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }
    public Medecin(Integer codemed,String nom, String prenom, String grade) {
    	this.codemed=codemed;
        this.nom = nom;
        this.prenom = prenom;
        this.grade = grade;
    }

    // 🔹 Getters et Setters
    public Integer getCodemed() {
        return codemed;
    }

    public void setCodemed(Integer codemed) {
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
