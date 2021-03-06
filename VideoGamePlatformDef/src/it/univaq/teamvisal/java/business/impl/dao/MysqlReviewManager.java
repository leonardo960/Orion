package it.univaq.teamvisal.java.business.impl.dao;

import java.util.LinkedList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.business.model.Review;
/**
 * DAO class to manage all storage/retrieval of information related to Reviews
 * @author Leonardo Formichetti
 *
 */
public class MysqlReviewManager extends MysqlManager implements JDBCReviewManager {

	/**
	 * Approves or rejects a Review based on the passed boolean value
	 * @param review the Review to be handled
	 * @param approved whether we are accepting or rejecting the Review
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 * @throws SQLException
	 */
	public void manageReview(Review review, boolean approved) throws DatabaseConnectionException, QueryException{
		Connection con = dbConnect();
		
		try{
		if(approved){
			String sql = "update review set status = ? where game = ? and player = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(2, review.getGamename());
			ps.setString(3, review.getUsername());
			ps.setString(1, "Approvata");
			ps.executeUpdate();
			
			ps.close();
			
		}else{
			String sql2 = "delete from review where game = ? and player = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, review.getGamename());
			ps2.setString(2, review.getUsername());
			ps2.executeUpdate();
			
			ps2.close();
		}
		
		con.close();
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	
	/**
	 * Lists all pending Reviews waiting to be analyzed by a moderator
	 * @return a List containing all such Reviews
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 * @throws SQLException
	 */
	public List<Review> getPendingReviews() throws DatabaseConnectionException, QueryException{
		List<Review> reviews = new LinkedList<Review>();
		Connection con = dbConnect();
		try{
		String sql = "select * from review where status = ? and player != ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, "In esame");
		ps.setString(2, MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername());
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			reviews.add( new Review(rs.getString("player"), rs.getString("game"), rs.getInt("vote"), rs.getString("text")));
		}
		
				
		con.close();
		ps.close();
		rs.close();
		
		return reviews;
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	
	/**
	 * Lists all the already approved Reviews for a specified Game
	 * @param game the game whose Reviews we want to read
	 * @return a List with all said Reviews
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 * @throws SQLException
	 */
	public List<Review> getReviewsForGame(Game game) throws DatabaseConnectionException, QueryException{
		List<Review> reviews = new LinkedList<Review>();
		Connection con = dbConnect();
		try{
		String sql = "select * from review where game = ? and status = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, game.getGameTitle());
		ps.setString(2, "Approvata");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			reviews.add(new Review(rs.getString("player"), rs.getString("game"), rs.getInt("vote"), rs.getString("text")));
		}
		
				
		con.close();
		ps.close();
		rs.close();
		
		return reviews;
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	
	/**
	 * Stores a Review in the Database, initally waiting to be approved or rejected
	 * @param review the Review to be stored
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 * @throws SQLException
	 */
	public void sendReview(Review review) throws DatabaseConnectionException, QueryException{
		Connection con = dbConnect();
		String sql = "insert into review values (?, ?, ?, ?, ?)";
		
		try{
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, review.getUsername());
		ps.setString(2, review.getGamename());
		ps.setInt(3, review.getVote());
		ps.setString(4, review.getText());
		ps.setString(5, "In esame");
		
		
		ps.executeUpdate();
		
		ps.close();
		con.close();
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	
	/**
	 * Checks whether the User has already sent a Review for the specified Game. (Only
	 * one Review per User per Game is allowed) 
	 * @param game the Game the User has reviewed
	 * @return true if the User has submitted a Review for the Game, false otherwise
	 * @throws SQLException
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 */
	public boolean hasUserSentReviewFor(Game game) throws DatabaseConnectionException, QueryException{
		Connection con = dbConnect();
		
		String sql = "select * from review where player = ? and game = ?";
		
		try{
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername());
		ps.setString(2, game.getGameTitle());
		
		ResultSet rs = ps.executeQuery();
		
		boolean result = rs.next();
		
		rs.close();
		ps.close();
		con.close();
		
		return result;
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	
}
