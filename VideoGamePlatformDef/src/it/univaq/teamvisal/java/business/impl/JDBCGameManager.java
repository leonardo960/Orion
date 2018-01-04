package it.univaq.teamvisal.java.business.impl;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.business.model.Review;
/**
 * DAO class which handles the storage of all information related to Games.
 * @author Leonardo Formichetti
 *
 */
public class JDBCGameManager extends JDBCManager {

	
	/**
	 * Checks whether the specified game exists in the Database, given its name, and
	 * eventually returns it.
	 * @param selectedGameTitle the game's name
	 * @return an object incapsulating all info related to the game
	 * @throws DatabaseConnectionException
	 * @throws SQLException
	 */
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
	
	
	/**
	 * Returns a List with all the games currently implemented inside Orion.
	 * @return the List containing the games
	 * @throws DatabaseConnectionException
	 * @throws SQLException
	 */
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
