package it.univaq.teamvisal.java.business.impl.dao;

import java.util.List;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Message;

public interface JDBCMessageManager {
	public void postMessage(Message message) throws DatabaseConnectionException, QueryException;
	public List<Message> checkForMessages() throws DatabaseConnectionException, QueryException;
	public void deleteMessage(int id) throws DatabaseConnectionException, QueryException;
}
