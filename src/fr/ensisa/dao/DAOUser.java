package fr.ensisa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.ensisa.model.User;

public class DAOUser extends DAOAbstractFacade<User> {
	
	public DAOUser()
	{
		super(User.class);
	}
	
	public List<String> getUserRoles(String name)
	{
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FitnessChalleng2021");
		EntityManager entitymanager1 =emfactory.createEntityManager( ); 
		entitymanager1.getTransaction().begin();
		//return entitymanager.createQuery("SELECT c from Role c where c.User_LOGIN LIKE :userName")
		//.setParameter("userName", name).getResultList();
		List<String> list = entitymanager1.createNativeQuery("Select d.interest FROM Drole d WHERE d.User_LOGIN = ?")
				.setParameter(1, name).getResultList();
		entitymanager1.close();
		
		return list;
		//return entitymanager.createNamedQuery("SELECT r FROM Role r WHERE r.User_LOGIN LIKE :custName")
				//.setParameter("custName", "Omar").getResultList();
		//.setParameter("userName", name).getResultList();
		
		
		
	}
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return super.getEntityManager();
	}

}
