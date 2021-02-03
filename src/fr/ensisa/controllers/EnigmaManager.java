/**
 * Copyright © 2021  	Hethsron Jedaël BOUEYA
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
package fr.ensisa.controllers;
import java.util.List;

/**
 *		@file            	EnigmaManager.java
 *      @details
 *
 *      @author          	Hethsron Jedaël BOUEYA (hethsron-jedael.boueya@uha.fr)
 *      					Omar CHICHAOUI (omar.chichaoui@uha.fr)
 *      				    Pranamika SOLANKI (pranamika.solanki@uha.fr)
 *
 *      @version         	0.0.1
 *      @date            	January, 25th 2021
 *
 *      @Copyright       	GPLv3+ : GNU GPL version 3 or later
 *                       	Licencied Material - Property of Us®
 *                       	© 2020 ENSISA (UHA) - All rights reserved.
 */
import fr.ensisa.dao.DAOEnigma;
import fr.ensisa.model.Enigma;

public class EnigmaManager {

	static DAOEnigma daoEnigma = new DAOEnigma();


	public static List<Enigma> getEnigmas()
	{
		List<Enigma> lv = daoEnigma.findAll();
		return lv;
	}

	public static Enigma getEnigma(long id) {

		Enigma u = daoEnigma.find(id);
		return u;
	}

	public static boolean createEnigma(long id, String goal, String answer) {
		Enigma en = daoEnigma.find(id);
		if (en == null) {
			//public User(long id, String username, String password, Role role) {
			daoEnigma.create(new Enigma(goal,  answer));
			return true;
		}
		return false;
	}
	
	public static int countEnigmas()
	{
		return daoEnigma.count();
	}
	
	public static void removeEnigma(Enigma enigma)
	{
		//daoUser.remove(user);
		daoEnigma.remove(enigma);
	}
	
	public static void updateEnigma(Enigma enigma)
	{
		//daoUser.edit(user);
		daoEnigma.edit(enigma);
	}
	
}