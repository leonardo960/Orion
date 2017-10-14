package it.univaq.teamvisal.java.business.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.User;

public class JDBCUserManager {
	//Bisognerà vedere quand'è che effettivamente si connette al DB
	//E quando invece opera solo in RAM; comunque fa entrambe le cose JDBCUserManager
	
	
	static private User currentUser;
	
	static public boolean storeUser(User user) throws SQLException, DatabaseConnectionException {
		Connection con = dbConnect();
		
		
		String query = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setInt(3, 0);
		ps.setString(4, "Recluta");
		ps.setString(5, "B");
		ps.setString(6, user.getNome());
		ps.setString(7, user.getCognome());
		
		ps.executeUpdate();
		
		String query2 = "INSERT INTO ACHIEVEMENT VALUES (?, ?, ?)";
		
		PreparedStatement ps2 = con.prepareStatement(query2);
		
		ps2.setString(1, user.getUsername());
		ps2.setString(2, "Trofeo di Benvenuto");
		Calendar cal = Calendar.getInstance();
		Date currentDate = new Date(cal.getTimeInMillis());
		ps2.setDate(3, currentDate);
		
		ps2.executeUpdate();
		
		con.close();
		ps.close();
		ps2.close();
		
		return true;
		
	}

	static public User doesUserExist(String username, String password) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		
		Statement statement = con.createStatement();
		
		String query = "select * from user where username = " + "\"" + username + "\""
						+ "and password = " + "\"" + password + "\"";
		ResultSet rs = statement.executeQuery(query);
		
		String user, pass, level, name, surname, type;
		int exp;
		
		if(rs.next()){
			user = rs.getString("username");
			pass = rs.getString("password");
			level = rs.getString("level");
			name = rs.getString("nome");
			surname = rs.getString("cognome");
			exp = rs.getInt("exp");
			type = rs.getString("type");
		}else{
			return null;
		}
		
		User u = new User(user, pass, name, surname);
		u.setExp(exp);
		u.setType(type);
		u.setLevel(level);
		
		String query2 = "SELECT TIME FROM ACHIEVEMENT WHERE PLAYER = ?";
		PreparedStatement ps = con.prepareStatement(query2);
		
		ps.setString(1, username);
		
		ResultSet rs2 = ps.executeQuery();
		
		while(rs2.next()){
			u.getLvlDates().add(rs2.getDate("time"));
		}
		
		
		
		
		String query4 = "SELECT * FROM mod_request WHERE mod_name = ?";
		PreparedStatement s4 = con.prepareStatement(query4);
		s4.setString(1, username);
			
		ResultSet rs4 = s4.executeQuery();
		if(rs4.next()){
			u.setRequestSent(true);
		}else{
			u.setRequestSent(false);
		}
			
		
		
		
		con.close();
		statement.close();
		ps.close();
		rs.close();
		rs2.close();
		s4.close();
		rs4.close();
		
		return u;
	}
	
	static public User getCurrentUser() {
		return currentUser;
	}

	
	static public void setCurrentUser(User user) {
		currentUser = user;
	}

	
	static public boolean checkDoubleUsers(User u) throws SQLException, DatabaseConnectionException {
		Connection con = dbConnect();
		
		Statement statement = con.createStatement();
		String query = "select * from user where username = ";
		query += "\"" + u.getUsername() + "\"" + ";";
			
		ResultSet rs = statement.executeQuery(query);
			
		if(rs.next()){
			rs.close();
			con.close();
			return true;
		}else{
			rs.close();
			con.close();
			return false;
		}
			
	}


	

	static private Connection dbConnect() throws DatabaseConnectionException{
		try {
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/oriondb?useSSL=true", "root", "lorenzo96" );
			return con;
		} catch (SQLException e) {
			throw new DatabaseConnectionException();
		}
	}
	
	static public boolean isUserLogged(){
		return currentUser != null;
	}

	public static void syncDB() throws DatabaseConnectionException, SQLException {
		Connection con = dbConnect();
		
		String query = "UPDATE USER SET exp = ? , level= ? WHERE username = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setInt(1, currentUser.getExp());
		ps.setString(2, currentUser.getLevel());
		ps.setString(3, currentUser.getUsername());
		
		//Inserire aggiornamento dei trofei nel db
		
		con.close();
		ps.close();
	}
	
	public static boolean sendModRequest(String pitch) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		
		String query = "INSERT INTO mod_request VALUES (?, ?)";
		
		PreparedStatement ps = con.prepareStatement(query);
		
		ps.setString(1, currentUser.getUsername());
		ps.setString(2, pitch);
		
		ps.executeUpdate();
		
		con.close();
		ps.close();
		
		currentUser.setRequestSent(true);
	
		return true;
	}
	
	public static TreeMap<String, String> getModeratorRequests() throws DatabaseConnectionException, SQLException{
		TreeMap<String, String> modRequests = new TreeMap<String, String>();
		Connection con = dbConnect();
		Statement statement = con.createStatement();
		String query = "SELECT * FROM mod_request";
		
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()){
			String user = rs.getString("mod_name");
			String pitch = rs.getString("pitch");
			modRequests.put(user, pitch);
		}
		
		con.close();
		statement.close();
		
		return modRequests;
		
	}
	
	public static void manageRequest(String username, boolean approved) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		String sql = "delete from mod_request where mod_name = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, username);
		statement.executeUpdate();
		
		if(approved){
			String sql2 = "update user set type = 'M' where username = ?";
			PreparedStatement statement2 = con.prepareStatement(sql2);
			statement2.setString(1, username);
			statement2.executeUpdate();
			statement2.close();
		}
		
		con.close();
		statement.close();
	}
	public static List<String> getModerators() throws DatabaseConnectionException, SQLException{
		List<String> list = new LinkedList<String>();
		
		Connection con = dbConnect();
		String sql = "select * from user where type = 'M'";
		Statement statement = con.createStatement();
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()){
			list.add(rs.getString("username") + " - " + rs.getString("nome") + " " + rs.getString("cognome"));
		}
		
		rs.close();
		statement.close();
		con.close();
		
		
		return list;
	}
	
	public static void derankModerator(String username) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		String sql = "update user set type = 'B' where username = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, username);
		statement.executeUpdate();
		
		statement.close();
		con.close();
	}
	
}
