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
import fr.ensisa.res.Role;

import java.util.*;

public class UserDao implements Dao<User, String> {

    private final Map<Long, User> store = Collections.synchronizedMap(new TreeMap<Long, User>());

    @Override
    public Optional<User> find(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Collection<User> findAll() {
        return store.values();
    }

    @Override
    public void persist(User user) {
        store.put(user.getId(), user);
    }

    @Override
    public void update(User user, String[] params) {
        user.setUsername(Objects.requireNonNull(params[0], "An user must have an username"));
        user.setPassword(Objects.requireNonNull(params[1], "An user must have a password"));
        store.put(user.getId(), user);
    }

    @Override
    public void remove(User user) {
        store.remove(user.getId());
    }

    @Override
    public long count() {
        return store.size();
    }

    @Override
    public boolean contains(String[] v) {
        for (User user : store.values()) {
            if (user.getUsername().equals(v[0]) && user.getPassword().equals(v[1]) && user.getRole() == Role.find(v[2]).get()) {
                return true;
            }
        }
        return false;
    }

}
