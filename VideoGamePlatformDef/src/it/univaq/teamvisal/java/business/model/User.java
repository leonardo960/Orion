package it.univaq.teamvisal.java.business.model;



import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class User extends Actor {
	private String nome, cognome;
	private String residenza;
    private int exp;
	private String level;
	private TreeMap<Trophy, Date> trophies;
	private boolean isMod;
	private String type;
	private boolean requestSent;

	
	public User(String username, 
			String password,
			String nome, 
			String cognome){
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		exp = 0;
		level = "Recluta";
		trophies = new TreeMap<Trophy, Date>();
		type = "B";
		requestSent = false;
	}
	
	
	public boolean equals(Object altro){
		if(!(altro instanceof User)){
			return false;
		}else{
		return this.username.equals(((User) altro).getUsername());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getResidenza() {
		return residenza;
	}

	public void setResidenza(String residenza) {
		this.residenza = residenza;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public TreeMap<Trophy, Date> getTrophies(){
		return trophies;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}


	public boolean isRequestSent() {
		return requestSent;
	}


	public void setRequestSent(boolean requestSent) {
		this.requestSent = requestSent;
	}



	
	
}
