package it.univaq.teamvisal.java.business.model;

public class Review {
	private String username;
	private String gamename;
	private int vote;
	private String text;
	
	//private String status; Da vedere se serve
	
	public Review(String username, String gamename, int vote, String text){
		this.username = username;
		this.gamename = gamename;
		this.vote = vote;
		this.text = text;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getGamename(){
		return gamename;
	}
	
	public int getVote(){
		return vote;
	}
	
	public String getText(){
		return text;
	}
}
