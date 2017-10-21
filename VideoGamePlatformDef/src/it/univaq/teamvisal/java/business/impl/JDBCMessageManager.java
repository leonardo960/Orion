package it.univaq.teamvisal.java.business.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Message;

public class JDBCMessageManager extends JDBCManager {

	
	public static void postMessage(Message message) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		String sql = "insert into message (sender, text, receiver) values (?, ?, ?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, message.getSender());
		ps.setString(2, message.getText());
		ps.setString(3, message.getReceiver());
		ps.executeUpdate();
		
		ps.close();
		con.close();
	}
	
	public static List<Message> checkForMessages() throws DatabaseConnectionException, SQLException{
		List<Message> messages = new LinkedList<Message>();
		
		Connection con = dbConnect();
        String sql = "select * from message where receiver = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, JDBCUserManager.getCurrentUser().getUsername());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			messages.add(new Message(rs.getString("text"), rs.getString("sender"), rs.getString("receiver"), rs.getInt("id")));
		}
		
		ps.close();
		rs.close();
		con.close();
		
		return messages;
	}
	
	public static void deleteMessage(int id) throws DatabaseConnectionException, SQLException{
		Connection con = dbConnect();
		String sql = "delete from message where id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		ps.close();
		con.close();
	}
	
	
}