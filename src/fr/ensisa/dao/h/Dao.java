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
package fr.ensisa.dao.h;
/**
 *		@file            	Dao.java
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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class Dao<T> {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("FitnessChalleng2021");
    private EntityManager entityManager;
    private Class<T> classEntity;
    private boolean state;

    public Dao(Class<T> classEntity) {
        this.classEntity = classEntity;
    }

    public EntityManager getEntityManager() {
        // Check if entity manager is null
        if (entityManager == null)
            // Creates entity manager
            entityManager = factory.createEntityManager();
        return entityManager;
    }

    public boolean persist(T entity) {
        // Update transaction state
        state = false;

        try {
            // Begin a transaction with the database
            getEntityManager().getTransaction().begin();

            // Persist the entity into the database
            getEntityManager().persist(entity);

            // Synchronize the persistence context to the underlying database.
            getEntityManager().flush();

            // Update transaction state
            state = true;
        }
        finally {
            if (state) {
                // Validate the transaction
                getEntityManager().getTransaction().commit();
            }
            else {
                // Rollback the transaction
                getEntityManager().getTransaction().rollback();
            }
        }

        return state;
    }

    public boolean update(T entity) {
        // Update transaction state
        state = false;

        try {
            // Begin a transaction with the database
            getEntityManager().getTransaction().begin();

            // Merge the new entity with the previous one
            getEntityManager().merge(entity);

            // Synchronize the persistence context to the underlying database.
            getEntityManager().flush();

            // Update transaction state
            state = true;
        }
        finally {
            if (state) {
                // Validate the transaction
                getEntityManager().getTransaction().commit();
            }
            else {
                // Rollback the transaction
                getEntityManager().getTransaction().rollback();
            }
        }

        return state;
    }

    public boolean remove(T entity) {
        // Update transaction state
        state = false;

        try {
            // Begin a transaction with the database
            getEntityManager().getTransaction().begin();

            // Remove the entity instance
            getEntityManager().remove(entity);

            // Synchronize the persistence context to the underlying database.
            getEntityManager().flush();

            // Update transaction state
            state = true;
        }
        finally {
            if (state) {
                // Validate the transaction
                getEntityManager().getTransaction().commit();
            }
            else {
                // Rollback the transaction
                getEntityManager().getTransaction().rollback();
            }
        }

        return state;
    }

    public T findById(Object id) {
        return getEntityManager().find(classEntity, id);
    }

    public T findByKey(String key, String value) {
        return (T) getEntityManager().createQuery("SELECT e FROM " + classEntity.getName() + " e WHERE e." + key + "=:" + key)
                .setParameter(key, value)
                .getSingleResult();
    }

    public List<T> findAll() {
        return (List<T>) getEntityManager().createQuery("SELECT * FROM " + classEntity.getName())
                .getResultList();
    }

    public int count() {
        return (int) getEntityManager().createQuery("SELECT COUNT(*) FROM " + classEntity.getName())
                .getSingleResult();
    }

}
