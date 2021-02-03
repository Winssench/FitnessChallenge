package fr.ensisa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Roleu {
	private String role;
	
	
	
	private User ownerRole;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	public User getOwnerRole() {
		return ownerRole;
	}

	public void setOwnerRole(User ownerRole) {
		this.ownerRole = ownerRole;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	public Roleu() {
		// TODO Auto-generated constructor stub
	}

}
