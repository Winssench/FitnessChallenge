package fr.ensisa.admin;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import fr.ensisa.dao.UserDao;
import fr.ensisa.model.Challenge;
import fr.ensisa.model.User;
import fr.ensisa.res.GamingMode;
import fr.ensisa.res.Role;

public class CreateEmployee {

	public static void main(String[] args) {

		User user = new User();
		user.setId(3);
		user.setUsername("user3");
		user.setPassword("opopok");
		user.setRole(Role.ADMINISTRATOR);

		user.addChallenge(new Challenge("courir 10km", 12, GamingMode.SOLO, user));

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FitnessChalleng2021");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();

		/*
		 * Employee employee = new Employee( ); employee.setEid( 1201 );
		 * employee.setEname( "Gopal" ); employee.setSalary( 40000 ); employee.setDeg(
		 * "Technical Manager" );
		 * 
		 * entitymanager.persist( employee ); entitymanager.getTransaction( ).commit( );
		 * 
		 * entitymanager.close( ); emfactory.close( );
		 */
		/*
		 * User user = new User(); user.setId(2); user.setUsername("user2");
		 * user.setPassword("opopok"); user.setRole(Role.PLAYER); //dao.persist(user);
		 */

		/*
		 * Getting a list UserDao dao = new UserDao(); Collection<User> liste =
		 * dao.findAll(); for (Iterator iterator = liste.iterator();
		 * iterator.hasNext();) { User user2 = (User) iterator.next();
		 * System.out.println(user2.getUsername()); }
		 */

		/*
		 * //updating trial String[] modifiedValue = {"toto", "toto"}; UserDao dao = new
		 * UserDao(); dao.update(dao.find(1).get(), modifiedValue);
		 */

		// size

		// UserDao dao = new UserDao();
		// System.out.println(dao.count());

	}
}
