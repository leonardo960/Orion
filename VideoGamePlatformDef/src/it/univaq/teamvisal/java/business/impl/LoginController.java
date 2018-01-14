package it.univaq.teamvisal.java.business.impl;


import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.NoUserException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
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
	 * @throws NoUserException
	 * @throws QueryException 
	 */
	public void login(String user, String pass) throws DatabaseConnectionException, NoUserException, QueryException{
		User u = MysqlDAOFactory.getInstance().getMysqlUserManager().doesUserExist(user, pass);
		if(u == null){
			throw new NoUserException();
		}else{
			 MysqlDAOFactory.getInstance().getMysqlUserManager().setCurrentUser(u);
		}
	}
}
