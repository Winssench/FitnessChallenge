package fr.ensisa.admin;

import java.util.List;

import fr.ensisa.dao.DAOUser;
import fr.ensisa.model.User;
import fr.ensisa.res.Role;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User();
		//user.setId(1);
		//user.setUsername("winssench1");
		//user.setPassword("opopok");
		//user.setRole(Role.ADMINISTRATOR);
		
		DAOUser daouser = new  DAOUser();
		List<String>  here = daouser.getUserRoles("Winssench");
		
		
		for(String a : here)
		{
			
			System.out.println(a);
		}
		

	}

}
