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
package fr.ensisa.model;
/**
 *		@file            	Segment.java
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
import javax.persistence.*;

@Entity
@Table
public class Segment {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private Segment next;
    private Challenge challengeOwner;
    private float distance;

    public Segment(String name, float distance) {
        super();
        this.name = name;
        this.next = null;
        this.challengeOwner = null;
        this.distance = distance;
    }

    public Segment() {
        super();
        this.name = null;
        this.next = null;
        this.challengeOwner = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Challenge getChallengeOwner() {
        return challengeOwner;
    }

    public void setChallengeOwner(Challenge challengeOwner) {
        this.challengeOwner = challengeOwner;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "next=" + next +
                ", distance=" + distance +
                '}';
    }

}
