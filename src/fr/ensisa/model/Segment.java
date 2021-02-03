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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Segment")
@Table(name = "segment")
public class Segment {

	public Segment() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private Segment next;

	private Challenge challengeOwner;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Challenge getChallengeOwner() {
		return challengeOwner;
	}

	public void setChallengeOwner(Challenge challengeOwner) {
		this.challengeOwner = challengeOwner;
	}

	private float distance;

	public Segment(float distance) {
		// this.obstacles = new ArrayList<>();
		this.next = null;

		this.distance = distance;
	}

	public Segment getNext() {
		return next;
	}

	public void setNext(Segment next) {
		this.next = next;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

}
