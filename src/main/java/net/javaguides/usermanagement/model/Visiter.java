package net.javaguides.usermanagement.model;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

@Entity 
@Table(name = "visiter")
public class Visiter implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codevisiter", nullable = false)
    private Integer codevisiter;  
    
    // Association vers Medecin : le champ codemed sera une clé étrangère
    @ManyToOne(optional = false)
    @JoinColumn(name = "codemed", referencedColumnName = "codemed")
    private Medecin medecin;  
    
    // Association vers Patient : le champ codepal sera une clé étrangère
    @ManyToOne(optional = false)
    @JoinColumn(name = "codepal", referencedColumnName = "codepal")
    private Patient patient;  
    
    @Column(name = "date", nullable = false)
    private Date date;  

    // Constructeur par défaut
    public Visiter() {}

    // Constructeur sans id (pour insertion)
    public Visiter(Medecin medecin, Patient patient, Date date) {
         this.medecin = medecin;
         this.patient = patient;
         this.date = date;
    }

    // Constructeur complet (pour édition, par exemple)
    public Visiter(Integer codevisiter, Medecin medecin, Patient patient, Date date) {
        this.codevisiter = codevisiter;
        this.medecin = medecin;
        this.patient = patient;
        this.date = date;
    }

    // Getters et Setters
    public Integer getCodevisiter() {
        return codevisiter;
    }

    public void setCodevisiter(Integer codevisiter) {
        this.codevisiter = codevisiter;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
