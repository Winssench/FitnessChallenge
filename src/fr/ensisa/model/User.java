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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(String login, String password, String lastname, String firstname) {
		super();
		this.login = login;
		this.password = password;
		this.lastname = lastname;
		this.firstname = firstname;
		this.role = "PLAYER";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
