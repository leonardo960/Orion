package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;

public class LogoutController {

	
	public void logout() throws DatabaseConnectionException, SQLException{
		if(JDBCUserManager.getCurrentUser() == null){
			return;
		}else{
			JDBCUserManager.syncDB();
			JDBCUserManager.setCurrentUser(null);
		}
	}
}
