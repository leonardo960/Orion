package it.univaq.teamvisal.java.business.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;

public class JDBCManager {
	
	static protected Connection dbConnect() throws DatabaseConnectionException{
		try {
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/oriondb?useSSL=true", "root", "lorenzo96" );
			return con;
		} catch (SQLException e) {
			throw new DatabaseConnectionException();
		}
	}
}
