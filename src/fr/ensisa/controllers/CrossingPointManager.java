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
 *		@file            	CrossingPointManager.java
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
import fr.ensisa.dao.CrossingPointDao;
import fr.ensisa.factory.Factory;
import fr.ensisa.model.CrossingPoint;
import java.util.List;

public class CrossingPointManager extends Manager {

    private static Factory<CrossingPointDao> factory = new Factory<CrossingPointDao>() {
        @Override
        public CrossingPointDao getDao() {
            return new CrossingPointDao();
        }
    };

    public static boolean create(String name, float score) {
        return factory.getDao().persist(new CrossingPoint(name, score));
    }

    public static CrossingPoint getById(long id) {
        return factory.getDao().findById(id);
    }

    public static CrossingPoint getByName(String name) {
        return factory.getDao().findByKey("name", name);
    }

    public static List<CrossingPoint> getAll() {
        return factory.getDao().findAll();
    }

    public static boolean delete(CrossingPoint crossingPoint) {
        return factory.getDao().remove(crossingPoint);
    }

    public static int count() {
        return factory.getDao().count();
    }

}
