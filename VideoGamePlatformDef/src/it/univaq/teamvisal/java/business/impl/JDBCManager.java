package it.univaq.teamvisal.java.business.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
/**
 * Superclass of all the DAO classes which contains the first and most basic
 * of functionalities, used by any DAO: connecting to the database
 * @author Leonardo Formichetti
 *
 */
public class JDBCManager {
	/**
	 * Attemps to enstablish a connection with the database
	 * @return a Connection object which holds all data pertaining the enstablished connection
	 * @throws DatabaseConnectionException
	 */
	static protected Connection dbConnect() throws DatabaseConnectionException{
		try {
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/oriondb?useSSL=true", "root", "lorenzo96" );
			return con;
		} catch (SQLException e) {
			throw new DatabaseConnectionException();
		}
	}
}
