package fr.ensisa.dao;

import fr.ensisa.model.User;

public class DAOUser extends DAOAbstractFacade<User> {
	
	public DAOUser()
	{
		super(User.class);
	}

}
