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
package fr.ensisa.dao;
/**
 *		@file            	UserDao.java
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
import fr.ensisa.model.User;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.*;

public class UserDao implements Dao<User> {

    private final Map<Long, User> store = Collections.synchronizedMap(new TreeMap<Long, User>());
    
    
    private   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FitnessChalleng2021");
    
    private EntityManager entitymanager = emfactory.createEntityManager( );
  
    
    
    @Override
    public Optional<User> find(long id) {  	
    	User user = entitymanager.find(User.class, id);
    	return Optional.ofNullable(user);
    }

    @Override
    public Collection<User> findAll() {
    	this.entitymanager.getTransaction().begin();
    	Collection<User> list = entitymanager.createQuery("Select a FROM User a", User.class).getResultList();
    	entitymanager.getTransaction().commit();
    	entitymanager.close();
    	emfactory.close();
    	return list;
    }

    @Override
    public void persist(User user) {

    	this.entitymanager.getTransaction().begin();
    	entitymanager.persist(user);
    	entitymanager.getTransaction().commit();
    	
    	entitymanager.close();
    	emfactory.close();
    }

    @Override
    public void update(User user, String[] params) {
    	this.entitymanager.getTransaction().begin();
        User oldUser= entitymanager.find(User.class, user.getId());
        oldUser.setUsername(Objects.requireNonNull(params[0], "An user must have an username"));
        oldUser.setPassword(Objects.requireNonNull(params[1], "An user must have a password"));
        entitymanager.getTransaction().commit();
        entitymanager.close();
    	emfactory.close();
        
    }

    @Override
    public void remove(User user) {
        //store.remove(user.getId());
    	this.entitymanager.getTransaction().begin();
  
    	User oldUser= entitymanager.find(User.class, user.getId());
    	entitymanager.remove(oldUser);
    	entitymanager.getTransaction().commit();
    	entitymanager.close();
     	emfactory.close();
    }
    
    

    @Override
    public long count() {
        //return store.size();
    	Collection<User> list = this.findAll();
    	return list.size();
    	
    	
    }

}
