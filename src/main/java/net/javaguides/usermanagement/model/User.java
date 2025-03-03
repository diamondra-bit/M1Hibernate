package net.javaguides.usermanagement.model;

import javax.persistence.Column;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name="users")
@SuppressWarnings("serial")
public class User implements Serializable{
	  private static final long serialVersionUID = 1L;  // 🔹 Ajoute un UID

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")

	private int id;
	
	@Column (name="name")
	protected String name;
	
	@Column (name="email")
	protected String email;
	
	@Column (name="country")
	protected String country;
	
	public User() {
		
	}
	
	public User(int id,String name,String email,String country) {
		super ();
		this.id=id;
		this.name=name;
		this.email=email;
		this.country=country;
	}
	public User(String name, String email, String country) {
		super ();
        this.name = name;
        this.email = email;
        this.country = country;
    }

    // Constructeur avec id (pour la récupération depuis la base)
   public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country=country;
	}
	
}