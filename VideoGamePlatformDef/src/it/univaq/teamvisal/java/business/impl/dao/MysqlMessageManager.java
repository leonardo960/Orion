package it.univaq.teamvisal.java.business.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Message;
/**
 * DAO which manages all storage and retrieval of Messages
 * @author Leonardo Formichetti
 *
 */
public class MysqlMessageManager extends MysqlManager implements JDBCMessageManager {

	/**
	 * Stores a message waiting for the User to read it
	 * @param message the Message to be stored
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 * @throws SQLException
	 */
	public void postMessage(Message message) throws DatabaseConnectionException, QueryException {
		Connection con = dbConnect();
		String sql = "insert into message (sender, text, receiver) values (?, ?, ?)";
		try{
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, message.getSender());
		ps.setString(2, message.getText());
		ps.setString(3, message.getReceiver());
		ps.executeUpdate();
		
		ps.close();
		con.close();
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	
	/**
	 * Checks whether there are Messages waiting to be read by the User
	 * @return a List of Messages for the User to read
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 * @throws SQLException
	 */
	public List<Message> checkForMessages() throws DatabaseConnectionException, QueryException{
		List<Message> messages = new LinkedList<Message>();
		
		Connection con = dbConnect();
		try{
        String sql = "select * from message where receiver = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			messages.add(new Message(rs.getString("text"), rs.getString("sender"), rs.getString("receiver"), rs.getInt("id")));
		}
		
		ps.close();
		rs.close();
		con.close();
		
		return messages;
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	/**
	 * Deletes the Message associated with the specified ID
	 * @param id the Message ID
	 * @throws DatabaseConnectionException
	 * @throws QueryException 
	 * @throws SQLException
	 */
	public void deleteMessage(int id) throws DatabaseConnectionException, QueryException{
		Connection con = dbConnect();
		try{
		String sql = "delete from message where id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		ps.close();
		con.close();
		}catch(SQLException e){
			throw new QueryException();
		}
	}
	
	
}
