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
/**
 *		@file            	Challenge.java
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
import fr.ensisa.res.GamingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity(name ="Challenge")
@Table(name = "challenge")
public class Challenge {
	
	
	public Challenge()
	{
		
	}

	@Id
    @GeneratedValue
    private long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    
    
    
    @OneToMany(
    		mappedBy = "challengeOwner" ,
			cascade = CascadeType.ALL,
			orphanRemoval = true
    		)
    private List<Segment> trip;
    
    

    public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getOwnerId() {	return owner;	}
	public void setOwnerId(User owner) {this.owner = owner;}

	private String name;
    private int maxUsers;
  
    private GamingMode mode;

    public Challenge(String name, int maxUsers, GamingMode mode, User owner) {
        this.name = name;
        this.maxUsers = maxUsers;
        this.mode = mode;
        this.trip = new ArrayList<>();
        this.owner = owner;
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public List<Segment> getTrip() {
        return trip;
    }

    public void setTrip(List<Segment> trip) {
        this.trip = trip;
    }

    public GamingMode getMode() {
        return mode;
    }

    public void setMode(GamingMode mode) {
        this.mode = mode;
    }

}
