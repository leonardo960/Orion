package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;

/**
 * Controller class that handles the call to the correct DAO class to handle
 * Moderator requests; it thus adds a layer in interfacing with the DAO 
 * @author Leonardo Formichetti
 *
 */
public class ModeratorRequestController {
	
	/**
	 * Simply asks the DAO to manage the moderator request given its pitch; here
	 * other operations not involving the DAO logic could be implemented. This justifies
	 * the existence of this layer
	 * @param pitch the moderator request's pitch
	 * @throws DatabaseConnectionException
	 * @throws SQLException
	 */
	public void sendRequest(String pitch) throws DatabaseConnectionException, SQLException{
		JDBCUserManager.sendModRequest(pitch);
	}
	
}
