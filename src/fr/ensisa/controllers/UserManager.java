package fr.ensisa.controllers;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import fr.ensisa.dao.DAOUser;
import fr.ensisa.model.User;
import fr.ensisa.res.Role;

public class UserManager {

	static DAOUser daoUser = new DAOUser();

	public static List<User> getUsers() {
		List<User> lv = daoUser.findAll();
		return lv;
	}

	public static User getUser(String login) {
		if (login == null)
			return null;

		User u = daoUser.find(login);
		return u;
	}

	public static User login(String login, String password) {
		User u = daoUser.find(login);
		if (u != null && u.getPassword().equals(password))
			return u;
		return null;
	}

	public static boolean createUser(String login, String password, String firstname, String lastname) {
		User u = daoUser.find(login);
		if (u == null) {
			//public User(long id, String username, String password, Role role) {
			daoUser.create(new User(login, password, firstname, lastname));
			return true;
		}
		return false;
	}
	public static  List<String> getUserRole(String login)
	{
		return daoUser.getUserRoles(login);
	}
	
	public static void removeUser(User user)
	{
		daoUser.remove(user);
	}
	
	public static void updateUser(User user)
	{
		daoUser.edit(user);
	}
}
