package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.GameplayController;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
/**
 * ScreenView for the Game Profile Screen. There's a brief description of the Game and
 * a button for leaving a review.
 * @author Leonardo Formichetti
 *
 */
public class GameProfileView extends ScreenViewSuper implements ScreenView {
	private JLabel gameTitle;
	private JButton reviewsBtn;
	private JLabel gameDescription;
	private Game displayedGame;
	private GameplayController gameplayController;
	
	public GameProfileView(){
		gameplayController = new GameplayController();
		screenName = "GAMEPROFILESCREEN";
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card.setLayout(null);
		
		gameDescription = new JLabel("");
		gameDescription.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		gameDescription.setVerticalAlignment(SwingConstants.TOP);
		gameDescription.setHorizontalAlignment(SwingConstants.CENTER);
		gameDescription.setForeground(Color.WHITE);
		gameDescription.setBounds(110, 129, 285, 138);
		card.add(gameDescription);
		
		gameTitle = new JLabel("");
		gameTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 25));
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitle.setForeground(Color.WHITE);
		gameTitle.setBounds(133, 11, 238, 110);
		card.add(gameTitle);
		
		JButton play = new JButton("Gioca!");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gameplayController.playGame(displayedGame);
				} catch(IOException | InterruptedException | DatabaseConnectionException | SQLException e1){
					if(e1 instanceof IOException){
						JOptionPane.showMessageDialog(card, "Errore di I/O nell'aprire il gioco", "Errore", JOptionPane.ERROR_MESSAGE);
					}else if(e1 instanceof InterruptedException){
						JOptionPane.showMessageDialog(card, "L'applicazione è ripartita senza aspettare il gioco", "Errore", JOptionPane.ERROR_MESSAGE);
					}else if(e1 instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Errore nella sincronizzazione con il database: database offline.", "Errore", JOptionPane.ERROR_MESSAGE);
					}else if(e1 instanceof SQLException){
						JOptionPane.showMessageDialog(card, "Errore nella sincronizzazione con il database: problemi con il database.", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		play.setBackground(Color.BLACK);
		play.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		play.setForeground(Color.WHITE);
		play.setFocusable(false);
		play.setBounds(107, 278, 130, 43);
		card.add(play);
		
		reviewsBtn = new JButton("Recensioni");
		reviewsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setScreen("GAMEREVIEWSCREEN");
				((GameReviewView) ScreenController.getLoadedScreens().get("GAMEREVIEWSCREEN")).populateList(displayedGame);
				((GameReviewView) ScreenController.getLoadedScreens().get("GAMEREVIEWSCREEN")).checkSendButtonVisibility();
				
			}
		});
		reviewsBtn.setBackground(Color.BLACK);
		reviewsBtn.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		reviewsBtn.setForeground(Color.WHITE);
		reviewsBtn.setFocusable(false);
		reviewsBtn.setBounds(265, 278, 130, 43);
		card.add(reviewsBtn);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen();
			}
		});
		back.setBackground(Color.BLACK);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setForeground(Color.WHITE);
		back.setFocusable(false);
		back.setBounds(190, 332, 130, 43);
		card.add(back);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}

	/**
	 * Populates the JTextFields in the ScreenView which hold the Game data.
	 * @param game the game whose data has to be displayed
	 */
	public void populateFields(Game game){
		gameTitle.setText(game.getGameTitle());
		gameDescription.setText("<html><p>" + game.getDescription() + "</p></html>");
		displayedGame = game;
	}
	
	
		
	
}
