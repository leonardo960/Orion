package it.univaq.teamvisal.java.business.impl;

import java.util.LinkedList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Review;

public class JDBCReviewManager extends JDBCManager {

	
	public static void manageReview(Review review, boolean approved) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		String sql = "update table review set status = ? where game = ?, player = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(2, review.getGamename());
		ps.setString(3, review.getUsername());
		
		if(approved){
			ps.setString(1, "Respinta");
		}else{
			ps.setString(1, "Approvata");
		}
		
		ps.executeUpdate();
		
		ps.close();
		con.close();
	}
	
	public static List<Review> getPendingReviews() throws DatabaseConnectionException, SQLException{
		List<Review> reviews = new LinkedList<Review>();
		Connection con = dbConnect();
		String sql = "select * from review where status = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, "In esame");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			reviews.add( new Review(rs.getString("player"), rs.getString("game"), rs.getInt("vote"), rs.getString("text")));
		}
		
				
		con.close();
		ps.close();
		rs.close();
		
		return reviews;
	}
	
}
