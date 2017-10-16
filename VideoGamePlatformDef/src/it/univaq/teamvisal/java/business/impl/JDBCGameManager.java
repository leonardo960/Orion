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

public class JDBCGameManager {

	private static Game selectedGame;
	private static String[] gamesList = {};

	public static Game doesGameExists(String selectedGameTitle) throws DatabaseConnectionException, SQLException {
		
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
	
	public static void retrieveReviews(String selectedGameTitle) throws SQLException, DatabaseConnectionException{

		Connection con = dbConnect();
		
		Statement statement = con.createStatement();
		
		String reviewsQuery = "select * from review where game = \"" + selectedGameTitle + "\" and status = \"Approvata\"";
		
		ResultSet rs = statement.executeQuery(reviewsQuery);
		
		List<Review> list = new LinkedList<Review>();
		
		while(rs.next()){
				//costruisce una review temporanea con i dati del database
		}		//e la aggiunge alla lista, che verrà poi passata al costruttore di Game
		
		con.close();
		statement.close();
		rs.close();
		
		selectedGame.setGameReviews(list);
		
	}
	
	static public Game getSelectedGame(){
		return selectedGame;
	}
	
	static public void setSelectedGame(Game game){
		selectedGame = game;
	}
	
	static public void listGames() throws DatabaseConnectionException, SQLException{ 
		
		Connection con = dbConnect();
		
		Statement statement = con.createStatement();
		
		String query = "select * from game";
		
		ResultSet rs = statement.executeQuery(query);
		
		int rowcount = 0;
		if (rs.last()) {
		  rowcount = rs.getRow();
		  rs.beforeFirst();
		}
		
		String[] list = new String[rowcount];
		
		for(int row = 0; row == rowcount - 1; rs.next()){
			list[row] = rs.getString("gamename");
		}
		
		con.close();
		statement.close();
		rs.close();
		
		setGamesList(list);
		
	}
	
	static public void backFromSelection(){
		selectedGame = null;
	}
	
	static private Connection dbConnect() throws DatabaseConnectionException{
		try {
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost/oriondb?useSSL=true", "root", "lorenzo96" );
			return con;
		} catch (SQLException e) {
			throw new DatabaseConnectionException();
		}
	}

	public static String[] getGamesList() {
		return gamesList;
	}

	public static void setGamesList(String[] gameList) {
		JDBCGameManager.gamesList = gameList;
	}
}
