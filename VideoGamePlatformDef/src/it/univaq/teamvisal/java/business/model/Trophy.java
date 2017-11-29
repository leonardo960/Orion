package it.univaq.teamvisal.java.business.model;

/**
 * Model class which represents a trophy, obtainable by user by playing games in Orion.
 * It encapsulates all its attributes as well as setters/getters
 * @author Leonardo Formichetti
 *
 */
public class Trophy implements Comparable<Trophy>{
	private String name;

	
	public Trophy(String name){
		this.name = name;
		
	}
	
	public String getName(){
		return name;
	}

	@Override
	public int compareTo(Trophy t) {
		return name.compareTo(t.name);
	}

	
}
