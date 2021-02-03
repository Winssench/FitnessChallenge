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
 *		@file            	SegmentManager.java
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
import fr.ensisa.dao.DAOSegment;
import fr.ensisa.model.Segment;
import fr.ensisa.model.User;

public class SegmentManager {

	static DAOSegment daoSegment = new DAOSegment();

	public static List<Segment> getSegments()
	{
		List<Segment> lv = daoSegment.findAll();
		return lv;
	}

	public static Segment getSegment(long id) {

		Segment u = daoSegment.find(id);
		return u;
	}

	public static boolean createSegment(long id, float distance) {
		Segment sm = daoSegment.find(id);
		if (sm == null) {
			//public User(long id, String username, String password, Role role) {
			daoSegment.create(new Segment(distance));
			return true;
		}
		return false;
	}
	
	public static int countSegments()
	{
		return daoSegment.count();
	}
	
	public static void removeSegment(Segment seg)
	{
		//daoUser.remove(user);
		daoSegment.remove(seg);
	}
	
	public static void updateSegment(Segment seg)
	{
		daoSegment.edit(seg);
	}
	
}