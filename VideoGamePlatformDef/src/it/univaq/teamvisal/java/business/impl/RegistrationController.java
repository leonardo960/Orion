package it.univaq.teamvisal.java.business.impl;

import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.UserAlreadyExistsException;
import it.univaq.teamvisal.java.business.model.User;

public class RegistrationController {

	public boolean register(String user, String pass, String name, String surname) throws SQLException, UserAlreadyExistsException, DatabaseConnectionException{
		User u = new User(user, pass, name, surname);
		if(!JDBCUserManager.checkDoubleUsers(u)){
		return JDBCUserManager.storeUser(u);
		}else{
		throw new UserAlreadyExistsException();
		}
	}
	
}
