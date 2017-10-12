package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;

public class ModeratorRequestController {
	
	public void sendRequest(String pitch) throws DatabaseConnectionException, SQLException{
		JDBCUserManager.sendModRequest(pitch);
	}
	
}
