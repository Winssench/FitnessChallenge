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
import fr.ensisa.dao.SegmentDao;
import fr.ensisa.factory.Factory;
import fr.ensisa.model.Segment;
import java.util.List;

public class SegmentManager extends Manager {

    private static Factory<SegmentDao> factory = new Factory<SegmentDao>() {
        @Override
        public SegmentDao getDao() {
            return new SegmentDao();
        }
    };

    public static boolean create(String name, long distance) {
        return factory.getDao().persist(new Segment(name, distance));
    }

    public static Segment getById(long id) {
        return factory.getDao().findById(id);
    }

    public static Segment getByName(String name) {
        return factory.getDao().findByKey("name", name);
    }

    public static List<Segment> getAll() {
        return factory.getDao().findAll();
    }

    public static boolean delete(Segment segment) {
        return factory.getDao().remove(segment);
    }

}
