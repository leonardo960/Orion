package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Segnale;
import utility.DatabaseConnectionException;


public class Storage {
	static private Connection con;
	
	static public void inizializza(){
		try {
			con = dbConnect();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
	}
	static private Connection dbConnect() throws DatabaseConnectionException{
		try {
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/dashboard?useSSL=true", "root", "lorenzo96" );
			return con;
		} catch (SQLException e) {
			throw new DatabaseConnectionException();
		}
	}
	
	static public void apriFinestraTemporale(Segnale segnale){
		String sql = "insert into finestra_temporale "
				+ "(robot, numSensore, sogliaSinistra, sogliaDestra) values (?, ?, ?, null)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, segnale.getRobotID().toString());
			ps.setByte(2, segnale.getSensorNumber());
			ps.setTimestamp(3, segnale.getTimestamp());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public void chiudiFinestraTemporale(Segnale segnale){
		String sql = "update finestra_temporale set sogliaDestra = ? where robot = ? "
				+ "and numSensore = ? order by sogliaSinistra desc limit 1";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, segnale.getRobotID().toString());
			ps.setByte(2, segnale.getSensorNumber());
			ps.setTimestamp(3, segnale.getTimestamp());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
