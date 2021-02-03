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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 *		@file            	Enigma.java
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
import fr.ensisa.res.ObstacleType;

@Entity(name = "Enigma")
@DiscriminatorValue("Enigma")
public class Enigma extends Obstacle {

	public Enigma() {

	}

	private String answer;

	public Enigma(String goal, String answer) {
		this.answer = answer;
		this.goal = goal;
		this.type = ObstacleType.PHYSICAL;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public ObstacleType getType() {
		return type;
	}

	public boolean verify(String answer) {
		return this.answer.equalsIgnoreCase(answer);
	}

}
