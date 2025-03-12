package net.javaguides.usermanagement.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity 
@Table(name = "patient")
public class Patient implements Serializable {
    
    private static final long serialVersionUID = 1L;  // UID pour éviter les problèmes de sérialisation
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codepal")
    private Integer codepal;  // ⚠️ `Integer` au lieu de `int` pour éviter les erreurs Hibernate

    @Column(name = "nom", nullable = false)
    private String nom;  // ⚠️ Respecte les conventions camelCase

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "sexe", nullable = false)
    private String sexe;
    
    @Column(name = "adresse", nullable = false)
    private String adresse;


    // 🔹 Constructeur par défaut obligatoire pour Hibernate
    public Patient() {}

    // 🔹 Constructeur sans id (Hibernate gère l'id auto)
    public Patient(String nom, String prenom, String sexe,String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse=adresse;
    }
    public Patient(Integer codepal,String nom, String prenom, String sexe,String adresse) {
    	this.codepal=codepal;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse=adresse;
    }

    // 🔹 Getters et Setters
    public Integer getCodepal() {
        return codepal;
    }

    public void setCodepal(Integer codepal) {
        this.codepal = codepal;
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

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
