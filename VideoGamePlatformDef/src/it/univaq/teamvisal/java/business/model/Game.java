package it.univaq.teamvisal.java.business.model;

import java.util.List;

public class Game{
	
	private String gameTitle;
	private String description;
	private List<Review> gameReviews;
	
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

	public List<Review> getGameReviews() {
		return gameReviews;
	}

	public void setGameReviews(List<Review> gameReviews) {
		this.gameReviews = gameReviews;
	}

}
