/**
 * Copyright © 2020  	Hethsron Jedaël BOUEYA
 * 						Omar CHICHAOUI
 * 					    Pranamika SOLANKI
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package fr.ensisa.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.ensisa.res.Role;

/**
 * @file User.java
 * @details
 *
 * @author Hethsron Jedaël BOUEYA (hethsron-jedael.boueya@uha.fr) Omar CHICHAOUI
 *         (omar.chichaoui@uha.fr) Pranamika SOLANKI (pranamika.solanki@uha.fr)
 *
 * @version 0.0.1
 * @date January, 25th 2021
 *
 * @Copyright GPLv3+ : GNU GPL version 3 or later Licencied Material - Property
 *            of Us® © 2020 ENSISA (UHA) - All rights reserved.
 */

@Entity
@Table
public class User {

	@Id
	private String login;

	private String firstname;
	private String lastname;
	private String password;
	
	/*
	
	@ElementCollection(targetClass=Role.class)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name="Drole")
    @Column(name="interest") // Column name in person_interest
    Collection<Role> roles;
    */
	

	
	public User(String login, String password, String lastname, String firstname) {
		super();
		this.login = login;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
	}

	
	public User() {
		super();
	}




	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	/*
	public  Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	*/


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
