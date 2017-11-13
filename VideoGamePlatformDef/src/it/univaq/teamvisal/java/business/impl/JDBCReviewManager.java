package it.univaq.teamvisal.java.business.impl;

import java.util.LinkedList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.business.model.Review;

public class JDBCReviewManager extends JDBCManager {

	
	public static void manageReview(Review review, boolean approved) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		
		
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
	}
	
	public static List<Review> getPendingReviews() throws DatabaseConnectionException, SQLException{
		List<Review> reviews = new LinkedList<Review>();
		Connection con = dbConnect();
		String sql = "select * from review where status = ? and player != ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, "In esame");
		ps.setString(2, JDBCUserManager.getCurrentUser().getUsername());
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			reviews.add( new Review(rs.getString("player"), rs.getString("game"), rs.getInt("vote"), rs.getString("text")));
		}
		
				
		con.close();
		ps.close();
		rs.close();
		
		return reviews;
	}
	
	public static List<Review> getReviewsForGame(Game game) throws DatabaseConnectionException, SQLException{
		List<Review> reviews = new LinkedList<Review>();
		Connection con = dbConnect();
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
	}
	
	public static void sendReview(Review review) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		String sql = "insert into review values (?, ?, ?, ?, ?)";
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, review.getUsername());
		ps.setString(2, review.getGamename());
		ps.setInt(3, review.getVote());
		ps.setString(4, review.getText());
		ps.setString(5, "In esame");
		
		
		ps.executeUpdate();
		
		ps.close();
		con.close();
	}
	
	public static boolean hasUserSentReviewFor(Game game) throws SQLException, DatabaseConnectionException{
		Connection con = dbConnect();
		String sql = "select * from review where player = ? and game = ?";
		
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, JDBCUserManager.getCurrentUser().getUsername());
		ps.setString(2, game.getGameTitle());
		
		ResultSet rs = ps.executeQuery();
		
		boolean result = rs.next();
		
		rs.close();
		ps.close();
		con.close();
		
		return result;
	}
	
}
