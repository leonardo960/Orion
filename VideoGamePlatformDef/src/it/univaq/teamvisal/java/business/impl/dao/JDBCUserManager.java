package it.univaq.teamvisal.java.business.impl.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.User;

public interface JDBCUserManager {
	public boolean storeUser(User user) throws DatabaseConnectionException, QueryException;
    public User doesUserExist(String username, String password) throws DatabaseConnectionException, QueryException;
    public User getCurrentUser();
    public void setCurrentUser(User user);
    public boolean checkDoubleUsers(User u) throws DatabaseConnectionException, QueryException;
    public boolean isUserLogged();
    public boolean sendModRequest(String pitch) throws DatabaseConnectionException, QueryException;
    public TreeMap<String, String> getModeratorRequests() throws DatabaseConnectionException, SQLException, QueryException;
    public void manageRequest(String username, boolean approved) throws DatabaseConnectionException, QueryException;
    public List<String> getModerators() throws DatabaseConnectionException, QueryException;
    public void derankModerator(String username) throws DatabaseConnectionException, QueryException;
    public void syncDB() throws DatabaseConnectionException, QueryException;
    public void updateUserExp(int exp) throws DatabaseConnectionException, QueryException;
    
}
