package it.univaq.teamvisal.java.presentation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCGameManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GameSelectionView extends ScreenViewSuper implements ScreenView{

	private JLabel titleLabel;
	private JButton selectButton;
	private JButton backButton;
	private JButton updateButton;
	private String selectedGame;
	private JList<Object> gameList;
	
	
	public GameSelectionView(){
		screenName = "GAMESELECTIONSCREEN";
	}
	
	/**
	 * @throws SQLException 
	 * @throws DatabaseConnectionException 
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize(){
		
		JPanel card = new JPanel();
		card.setBounds(0, 0, 500, 500);
		card.setLayout(null);
		
		titleLabel =  new JLabel("Selezione Giochi");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 31));
		titleLabel.setForeground(new Color(51, 153, 255));
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setLocation(124, 23);
		titleLabel.setSize(252, 46);
		card.add(titleLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(124, 97, 252, 205);
		card.add(scrollPane);
		
		try{
			
			JDBCGameManager.listGames();
			gameList = new JList<Object>(JDBCGameManager.getGamesList());
			
		} catch (DatabaseConnectionException | SQLException e) {
			gameList = new JList<Object>();
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "I giochi non sono stati caricati perché il database risulta offline");
			}else if(e instanceof SQLException){
				JOptionPane.showMessageDialog(card, "I giochi non sono stati caricati a causa di un problema con il database.\nRitentare premendo il pulsante Aggiorna");
			}
		}
		
		gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedGame = (String) gameList.getSelectedValue();
			}
		});
		
		gameList.setForeground(new Color(255, 255, 102));
		scrollPane.setViewportView(gameList);
		gameList.setBackground(new Color(0, 0, 51));
		gameList.setBorder(null);
		
		selectButton = new JButton("Vai al gioco");
		selectButton.setBackground(Color.BLACK);
		selectButton.setForeground(Color.WHITE);
		selectButton.setLocation(155, 350);
		selectButton.setSize(190, 35);
		card.add(selectButton);
		
		backButton = new JButton("Indietro");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen("GAMESELECTIONSCREEN");
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(Color.BLACK);
		backButton.setLocation(155, 408);
		backButton.setSize(190, 35);
		card.add(backButton);
		
		updateButton = new JButton("Aggiorna");
		updateButton.setBackground(new Color(0, 0, 0));
		updateButton.setForeground(new Color(51, 153, 255));
		updateButton.setBounds(124, 301, 252, 23);
		card.add(updateButton);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\workspace\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return null;
	}

	@Override
	protected void clearTextFields() {
		// NEVER USED
		
	}

}
