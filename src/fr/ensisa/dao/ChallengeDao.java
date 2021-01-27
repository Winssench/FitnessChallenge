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
 *		@file            	ChallengeDao.java
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
import fr.ensisa.model.Challenge;
import fr.ensisa.res.GamingMode;
import java.util.*;

public class ChallengeDao implements Dao<Challenge> {

    private final Map<Long, Challenge> store = Collections.synchronizedMap(new TreeMap<Long, Challenge>());

    @Override
    public Optional<Challenge> find(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Collection<Challenge> findAll() {
        return store.values();
    }

    @Override
    public void persist(Challenge challenge) {
        store.put(challenge.getId(), challenge);
    }

    @Override
    public void update(Challenge challenge, String[] params) {
        challenge.setName(Objects.requireNonNull(params[0], "A challenge must have a name"));
        challenge.setMaxUsers(Objects.requireNonNull(Integer.valueOf(params[0]), "A challenge must have a maximum number of users"));
        challenge.setMode(Objects.requireNonNull(GamingMode.find(params[0]).get(), "A challenge must have a gaming mode"));
        store.put(challenge.getId(), challenge);
    }

    @Override
    public void remove(Challenge challenge) {
        store.remove(challenge.getId());
    }

    @Override
    public long count() {
        return store.size();
    }

}
