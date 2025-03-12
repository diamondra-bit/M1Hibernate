package net.javaguides.usermanagement.model;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;

@Entity 
@Table(name = "visiter")
public class Visiter implements Serializable {
    
    private static final long serialVersionUID = 1L;  // UID pour éviter les problèmes de sérialisation
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "codevisiter",nullable = false)
    private Integer codevisiter;  
    
    @Column(name = "codemed",nullable = false)
    private Integer codemed;  // ⚠️ `Integer` au lieu de `int` pour éviter les erreurs Hibernate

    @Column(name = "codepal", nullable = false)
    private Integer codepal;  
    
    @Column(name = "date", nullable = false)
    private Date date;  

   
    // 🔹 Constructeur par défaut obligatoire pour Hibernate
    public Visiter() {}

    // 🔹 Constructeur sans id (Hibernate gère l'id auto)
    public Visiter(Integer codevisiter,Integer codemed,Integer codepal,Date date) {
        this.codevisiter = codevisiter;
        this.codemed = codemed;
        this.codepal = codepal;
        this.date = date;
    }
    public Visiter(Integer codemed,Integer codepal,Date date) {
         this.codemed = codemed;
         this.codepal = codepal;
         this.date = date;
    }

    // 🔹 Getters et Setters
    public Integer getCodevisiter() {
        return codevisiter;
    }

    public void setCodevisiter(Integer codevisiter) {
        this.codevisiter = codevisiter;
    }

    public Integer getCodemed() {
        return codemed;
    }

    public void setCodemed(Integer codemed) {
        this.codemed = codemed;
    }
    
    public Integer getCodepal() {
        return codepal;
    }

    public void setCodepal(Integer codepal) {
        this.codepal = codepal;
    }

    public Date getDate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }
   
}
