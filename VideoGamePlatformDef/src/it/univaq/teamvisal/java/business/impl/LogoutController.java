package it.univaq.teamvisal.java.business.impl;


import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;

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
	 * @throws QueryException 
	 */
	public void logout() throws DatabaseConnectionException, QueryException{
		if( MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser() == null){
			return;
		}else{
			 MysqlDAOFactory.getInstance().getMysqlUserManager().syncDB();
			 MysqlDAOFactory.getInstance().getMysqlUserManager().setCurrentUser(null);
		}
	}
}
