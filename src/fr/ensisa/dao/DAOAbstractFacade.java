package fr.ensisa.dao;

import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Nom du serveur : mysql.iutrs.unistra.fr 
 * Gestion des bases : https://webetu.iutrs.unistra.fr/phpmyadmin/ 
 * Base : Airport 
 * Password : VCPej4PtQQcN
 */
public abstract class DAOAbstractFacade<T> {

	//@PersistenceUnit(unitName = "Airport")
	private EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FitnessChalleng2021");
	//@PersistenceContext(unitName = "Airport")
	private EntityManager em;

	private Class<T> classeEntite;

	/**
	 * Constructeur
	 * 
	 * @param classeEntite La classe de l'objet metier
	 */
	public DAOAbstractFacade(Class<T> classeEntite) {
		this.classeEntite = classeEntite;
	}

	/**
	 * Methode abstraite a definir dans chaque sous-classe qui renvoie
	 * l'EntityManager correspondant a la classe.
	 * 
	 * @return l'entity manager
	 */
	protected EntityManager getEntityManager() {
		if (em == null)
			em = emfactory.createEntityManager();
		return em;
	}

	/**
	 * Methode de creation d'un objet (ajout dans la base).
	 * 
	 * @param entite
	 */
	public T create(T entite) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entite);
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
		return entite;
	}

	/**
	 * Methode de modification d'un objet.
	 * 
	 * @param entite
	 */
	public void edit(T entite) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(entite);
		getEntityManager().getTransaction().commit();
	}

	/**
	 * Methode de suppression d'un objet.
	 * 
	 * @param entite
	 */
	public void remove(T entite) {
		getEntityManager().remove(getEntityManager().merge(entite));
	}

	/**
	 * Methode de recherche d'un objet a partir de son identifiant.
	 * 
	 * @param id
	 * @return
	 * @return
	 */
	public T find(Object id) {
		return getEntityManager().find(classeEntite, id);
	}

	/**
	 * Methode recherchant tous les objets de ce type.
	 * 
	 * @return
	 */
	public ArrayList<T> findAll() {
		@SuppressWarnings("unchecked")
		CriteriaQuery<T> cq = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(classeEntite));
		Vector<T> v = (Vector<T>) getEntityManager().createQuery(cq).getResultList();
		if (v != null)
			return new ArrayList<T>(v);
		return null;
	}

	/**
	 * Methode renvoyant le nombre d'objet de ce type.
	 * 
	 * @return
	 */
	public int count() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(classeEntite);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
}
