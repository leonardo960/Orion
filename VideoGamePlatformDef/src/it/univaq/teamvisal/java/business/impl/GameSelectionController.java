package it.univaq.teamvisal.java.business.impl;

import it.univaq.teamvisal.java.business.model.Game;

public class GameSelectionController {
	
	public void gameSelected(String title){
		
		Game selectedGame = JDBCGameManager.doesGameExists(title);
		
	}

}
