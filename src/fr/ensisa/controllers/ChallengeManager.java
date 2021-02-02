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
/**
 *		@file            	ChallengeManager.java
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
import fr.ensisa.dao.ChallengeDao;
import fr.ensisa.factory.Factory;
import fr.ensisa.model.Challenge;
import java.util.List;

public class ChallengeManager extends Manager {

    private static Factory<ChallengeDao> factory = new Factory<ChallengeDao>() {
        @Override
        public ChallengeDao getDao() {
            return new ChallengeDao();
        }
    };

    public static boolean create(String name, int maxUsers) {
        return factory.getDao().persist(new Challenge(name, maxUsers));
    }

    public static Challenge getById(long id) {
        return factory.getDao().findById(id);
    }

    public static Challenge getByName(String name) {
        return factory.getDao().findByKey("name", name);
    }

    public static List<Challenge> getAll() {
        return factory.getDao().findAll();
    }

    public static boolean delete(Challenge challenge) {
        return factory.getDao().remove(challenge);
    }

    public static int count() {
        return factory.getDao().count();
    }

}
