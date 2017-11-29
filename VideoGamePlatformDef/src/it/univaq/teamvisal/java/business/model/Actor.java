package it.univaq.teamvisal.java.business.model;

/**
 * Java utility class to join attributes common to all users of the system: username and
 * password.
 * @author Leonardo Formichetti
 *
 */
public class Actor {
	protected String username, password;
	
	public String getPassword(){
		return password;
	}
	
	public String getUsername(){
		return username;
	}
	
}
