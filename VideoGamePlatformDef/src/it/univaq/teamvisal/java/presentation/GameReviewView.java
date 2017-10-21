package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.model.Game;

public class GameReviewView extends ScreenViewSuper implements ScreenView {
	private JPanel card;
	private Game selectedGame;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card = new JPanel();
		card.setLayout(null);
		
		return card;
	}

	@Override
	protected void clearTextFields() {
		//Never Used		
	}
	
	public void populateList(Game game){
		//Mostra le recensioni del gioco selezionato in precedenza
	}
}
