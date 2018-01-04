package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;

/**
 * Controller class which manages the logic of the logout process
 * @author Leonardo Formichetti
 *
 */
public class LogoutController {

	/**
	 * Logs the User out of the system by first setting the current User to null and then
	 * attempting to syncronize the information coming from the platform with that of the
	 * database
	 * @throws DatabaseConnectionException
	 * @throws SQLException
	 */
	public void logout() throws DatabaseConnectionException, SQLException{
		if(JDBCUserManager.getCurrentUser() == null){
			return;
		}else{
			JDBCUserManager.syncDB();
			JDBCUserManager.setCurrentUser(null);
		}
	}
}
