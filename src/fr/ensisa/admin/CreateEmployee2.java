package fr.ensisa.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import fr.ensisa.model.Challenge;
import fr.ensisa.model.CrossingPoint;
import fr.ensisa.model.Enigma;
import fr.ensisa.model.Obstacle;
import fr.ensisa.model.Roleu;
import fr.ensisa.model.Segment;
import fr.ensisa.model.User;
import fr.ensisa.res.GamingMode;
import fr.ensisa.res.Role;

public class CreateEmployee2 {

	public static void main(String[] args) {

		User user = new User();
		/*
		user.setId(6);
		user.setUsername("winssench1");
		user.setPassword("opopok");
		user.setRole(Role.ADMINISTRATOR);
		*/
		
		user.setLogin("Winssench");
		user.setFirstname("wins");
		user.setLastname("ch");
		user.setPassword("hello");
		
		
		
		
		
		Roleu ra = new Roleu();
		ra.setRole("ADMIN");
		Roleu ra2 = new Roleu();
		ra2.setRole("PLAYER");
		
		ra.setOwnerRole(user);
		ra2.setOwnerRole(user);
		
		
		
		User user1 = new User();
		/*
		user.setId(6);
		user.setUsername("winssench1");
		user.setPassword("opopok");
		user.setRole(Role.ADMINISTRATOR);
		*/
		
		user1.setLogin("Omar");
		user1.setFirstname("wins");
		user1.setLastname("ch");
		user1.setPassword("hello");

		Roleu r1 = new Roleu();
		r1.setRole("ADMIN");
		Roleu r2 = new Roleu();
		r1.setRole("PLAYER");
		
		r1.setOwnerRole(user1);
		r2.setOwnerRole(user1);
		

		
		Challenge chall = new Challenge("sauter", 12, GamingMode.SOLO, user);
		
		//Segment
		Segment segA = new Segment();
		//crossingPoints
		CrossingPoint a = new CrossingPoint();
		a.setName("A");
		a.setScore(0);
		a.setSegmentOwner(segA);
		CrossingPoint b = new CrossingPoint();
		b.setName("B");
		b.setScore(10);
		b.setSegmentOwner(segA);
		/*
		List<CrossingPoint> listcross = new ArrayList<CrossingPoint>();
		listcross.add(a);
		listcross.add(b);
		*/
		
		//segA.setCrossingPoints(listcross);
		
		//
		Obstacle obsTest = new Enigma("hello", "this is a test");
		obsTest.setSegmentOwner(segA);
		/*
		List<Obstacle> listObs = new ArrayList<Obstacle>();
		listObs.add(obsTest);
		segA.setObstacles(listObs);
		*/
		
		segA.setChallengeOwner(chall);
		
		//Segment
		Segment segB = new Segment();
		//crossingPoints
		CrossingPoint c = new CrossingPoint();
		c.setName("C");
		c.setScore(0);
		c.setSegmentOwner(segB);
		CrossingPoint d = new CrossingPoint();
		d.setName("D");
		d.setScore(10);
		d.setSegmentOwner(segB);
		/*
		List<CrossingPoint> listcrossSecond = new ArrayList<CrossingPoint>();
		listcross.add(c);
		listcross.add(d);
		
		segB.setCrossingPoints(listcrossSecond);
		 */
		
		//
		Obstacle obsTestSec = new Enigma("hello2", "this is a test2");
		obsTestSec.setSegmentOwner(segB);
		/*
		List<Obstacle> listObsSec = new ArrayList<Obstacle>();
		listObsSec.add(obsTestSec);
		segB.setObstacles(listObsSec);
		*/
		segB.setChallengeOwner(chall);
		
		//somme of segA and B
		/*
		List<Segment> route = new ArrayList<Segment>();
		route.add(segA);
		route.add(segB);
		
		challengeComposed.setTrip(route);
		*/
		//segB.setChallengeOwner(chall);
		//user.addChallenge(chall);
		chall.setOwner(user);

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FitnessChalleng2021");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		
		entitymanager.persist(r2);
		entitymanager.persist(r1);
		entitymanager.persist(ra);
		entitymanager.persist(ra2);

		entitymanager.persist(user);
		entitymanager.persist(user1);
		entitymanager.persist(chall);
		entitymanager.persist(obsTestSec);
		entitymanager.persist(d);
		entitymanager.persist(c);
		entitymanager.persist(a);
		entitymanager.persist(b);
		entitymanager.persist(d);
		entitymanager.persist(obsTest);
		entitymanager.persist(segA);
		entitymanager.persist(segB);
		
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
