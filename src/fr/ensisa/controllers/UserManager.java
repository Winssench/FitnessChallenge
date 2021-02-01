package fr.ensisa.controllers;

import java.util.List;

import fr.ensisa.dao.DAOUser;
import fr.ensisa.dao.UserDao;
import fr.ensisa.factory.UserFactory;
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

	public static boolean createUser(long login, String username, String password, Role role) {
		User u = daoUser.find(login);
		if (u == null) {
			//public User(long id, String username, String password, Role role) {
			daoUser.create(new User(login, username, password, role));
			return true;
		}
		return false;
	}
}
