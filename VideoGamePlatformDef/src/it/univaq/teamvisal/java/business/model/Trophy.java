package it.univaq.teamvisal.java.business.model;

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
