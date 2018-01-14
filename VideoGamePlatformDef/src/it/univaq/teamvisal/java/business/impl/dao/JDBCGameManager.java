package it.univaq.teamvisal.java.business.impl.dao;

import java.util.List;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Game;

public interface JDBCGameManager {
	public Game doesGameExist(String game) throws DatabaseConnectionException, QueryException;
	public List<Game> listGames() throws DatabaseConnectionException, QueryException;
	
}
