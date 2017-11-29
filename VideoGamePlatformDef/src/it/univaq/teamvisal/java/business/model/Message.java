package it.univaq.teamvisal.java.business.model;

/**
 * Model class which represents a message sent by moderators/the system and received by
 * any user. It encapsulates all its attributes as well as setters/getters
 * @author Leonardo Formichetti
 *
 */
public class Message {
	private String text;
	private String sender;
	private String receiver;
	private int id;
	
	public Message(String text, String sender, String receiver, int id){
		this(text, sender, receiver);
		this.id = id;
	}
	
	public Message(String text, String sender, String receiver){
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public String getText(){
		return text;
	}
	
	public String getSender(){
		return sender;
	}
	
	public String getReceiver(){
		return receiver;
	}
	
	public int getID(){
		return id;
	}
}
