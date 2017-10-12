package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.NoUserException;
import it.univaq.teamvisal.java.business.model.User;

public class LoginController {
	
	public void login(String user, String pass) throws DatabaseConnectionException, SQLException, NoUserException{
		User u = JDBCUserManager.doesUserExist(user, pass);
		if(u == null){
			throw new NoUserException();
		}else{
			JDBCUserManager.setCurrentUser(u);
		}
	}
}
