package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.UserAlreadyExistsException;
import it.univaq.teamvisal.java.business.model.User;

/**
 * Controller class that handles the call for the correct DAO class to register new
 * accounts
 * @author Leonardo Formichetti
 *
 */
public class RegistrationController {
	/**
	 * Simply asks the corresponding DAO to register a new account given its data; here
	 * other operations not involving the DAO logic could be implemented. This justifies
	 * the existence of this layer
	 * @param user the username of the new account to be created
	 * @param pass the password of the new account to be created
	 * @param name the name of player associated with the new account
	 * @param surname the surname of the player associated with the new account
	 * @return true if the registration went successful; throws exceptions otherwise
	 * @throws SQLException
	 * @throws UserAlreadyExistsException
	 * @throws DatabaseConnectionException
	 */
	public boolean register(String user, String pass, String name, String surname) throws SQLException, UserAlreadyExistsException, DatabaseConnectionException{
		User u = new User(user, pass, name, surname);
		if(!JDBCUserManager.checkDoubleUsers(u)){
		return JDBCUserManager.storeUser(u);
		}else{
		throw new UserAlreadyExistsException();
		}
	}
	
}
