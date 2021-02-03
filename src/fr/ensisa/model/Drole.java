package fr.ensisa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Drole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	public Drole() {

	}

}
