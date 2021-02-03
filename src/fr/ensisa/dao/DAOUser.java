package fr.ensisa.dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import fr.ensisa.model.User;

public class DAOUser extends DAOAbstractFacade<User> {
	
	public DAOUser()
	{
		super(User.class);
	}
	

	
	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return super.getEntityManager();
	}
	public void createFull(User user)
	{
		
		try {
			UserTransaction transaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
		
			transaction.begin();
			getEntityManager().joinTransaction();
			getEntityManager().persist(user);
			getEntityManager().flush();
			transaction.commit();
		} catch (NamingException | NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		
	}

}
