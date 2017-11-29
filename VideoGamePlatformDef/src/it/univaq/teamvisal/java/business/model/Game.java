package it.univaq.teamvisal.java.business.model;

/**
 * Model class which represents a videogame. It encapsulates all its attributes as well as
 * setters/getters
 * @author Leonardo Formichetti
 *
 */
public class Game{
	
	private String gameTitle;
	private String description;
	
	
	public Game(String title, String descr) {
		setGameTitle(title);
		setDescription(descr);
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
