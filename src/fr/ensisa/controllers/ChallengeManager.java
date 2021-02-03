package fr.ensisa.controllers;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import fr.ensisa.dao.DAOChallenge;
import fr.ensisa.model.Challenge;
import fr.ensisa.model.User;
import fr.ensisa.res.GamingMode;


public class ChallengeManager {
	
	static DAOChallenge daoChallenge = new DAOChallenge();


	public static List<Challenge> getChallengs()
	{
		List<Challenge> lv = daoChallenge.findAll();
		return lv;
	}

	public static Challenge getChallenge(long id) {

		Challenge u = daoChallenge.find(id);
		return u;
	}


	//public Challenge(String name, int maxUsers, GamingMode mode, User owner) {
	public static boolean createChallenge(long id,String name, int maxUsers, GamingMode mode, User owner) throws SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		Challenge ch = daoChallenge.find(id);
		if (ch == null) {
			//public User(long id, String username, String password, Role role) {
			daoChallenge.create(new Challenge(name,  maxUsers, mode,  owner));
			return true;
		}
		return false;
	}
	
	public static int countChallenges()
	{
		return daoChallenge.count();
	}
	
	public static void removeChallange(Challenge ch)
	{
		daoChallenge.remove(ch);
	}
	public static void updateChallange(Challenge ch)
	{
		daoChallenge.edit(ch);;
	}
	
	public static void createFullChallenge(Challenge challenge) {
		//daoUser.createFull(user);
		daoChallenge.create(challenge);
	}
	
	

}
