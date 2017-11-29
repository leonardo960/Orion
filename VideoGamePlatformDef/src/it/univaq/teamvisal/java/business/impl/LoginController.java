package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.NoUserException;
import it.univaq.teamvisal.java.business.model.User;

/**
 * Controller class which manages all the logic related to the login procedure
 * @author Leonardo Formichetti
 *
 */
public class LoginController {
	
	/**
	 * Logs the user in the system by first checking if a User with the entered information
	 * exists and then setting it as the currently active User
	 * @param user the specified username
	 * @param pass the specified password
	 * @throws DatabaseConnectionException
	 * @throws SQLException
	 * @throws NoUserException
	 */
	public void login(String user, String pass) throws DatabaseConnectionException, SQLException, NoUserException{
		User u = JDBCUserManager.doesUserExist(user, pass);
		if(u == null){
			throw new NoUserException();
		}else{
			JDBCUserManager.setCurrentUser(u);
		}
	}
}
