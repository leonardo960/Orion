package it.univaq.teamvisal.java.business.impl;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.business.model.Review;

public class JDBCGameManager extends JDBCManager {

	

	public static Game doesGameExist(String selectedGameTitle) throws DatabaseConnectionException, SQLException {
		
		Connection con = dbConnect();
		
		Statement statement = con.createStatement();
		
		String gameQuery = "select * from game where gamename = \"" + selectedGameTitle + "\"";
		
		ResultSet rs = statement.executeQuery(gameQuery);
		
		String description;
		
		if(rs.next()){
			description = rs.getString("description");
		} else {
			return null;
		}
		
		Game g = new Game(selectedGameTitle, description);
		
		con.close();
		statement.close();
		rs.close();

		return g;
	}
	
	public static List<Review> retrieveReviews(String selectedGameTitle) throws SQLException, DatabaseConnectionException{

		List<Review> reviews = new LinkedList<Review>();
		
		Connection con = dbConnect();
		
		Statement statement = con.createStatement();
		
		String reviewsQuery = "select * from review where game = \"" + selectedGameTitle + "\" and status = \"Approvata\"";
		
		ResultSet rs = statement.executeQuery(reviewsQuery);
		
		
		while(rs.next()){
			reviews.add( new Review(rs.getString("player"), rs.getString("game"), rs.getInt("vote"), rs.getString("text")));
		}
		
		con.close();
		statement.close();
		rs.close();
		
		
		return reviews;
	}
	
	
	
	static public List<Game> listGames() throws DatabaseConnectionException, SQLException{ 
		List<Game> games = new LinkedList<Game>();
		
		Connection con = dbConnect();
		
		Statement statement = con.createStatement();
		
		String query = "select * from game";
		
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()){
			games.add(new Game(rs.getString("gamename"), rs.getString("description")));
		}
		
		con.close();
		statement.close();
		rs.close();
		
		return games;
	}
	
}
