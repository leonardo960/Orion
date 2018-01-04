package it.univaq.teamvisal.java.presentation;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.JDBCGameManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.awt.event.ActionEvent;

/**
 * ScreenView for selecting which Game the User wants to play. It displays them in a
 * List fashion.
 * @author Leonardo Formichetti
 *
 */
public class GameSelectionView extends ScreenViewSuper implements ScreenView{

	private JLabel titleLabel;
	private JButton selectButton;
	private JButton backButton;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scrollPane;
	private List<Game> games;
	private TreeMap<String, Game> listRowToGame;
	
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
		card.setBounds(0, 0, 500, 500);
		card.setLayout(null);
		
		titleLabel =  new JLabel("Selezione Giochi");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 31));
		titleLabel.setForeground(new Color(51, 153, 255));
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setLocation(124, 23);
		titleLabel.setSize(252, 46);
		card.add(titleLabel);
	
		
		selectButton = new JButton("Vai al gioco");
		selectButton.setFocusable(false);
		selectButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(card, "Per favore, seleziona prima il gioco dalla lista.", "Gioco non selezionato.", JOptionPane.WARNING_MESSAGE);
				}else{
					goToGameProfile();
				}
				
			}
		});
		selectButton.setBackground(Color.BLACK);
		selectButton.setForeground(Color.WHITE);
		selectButton.setLocation(155, 350);
		selectButton.setSize(190, 35);
		card.add(selectButton);
		
		backButton = new JButton("Indietro");
		backButton.setFocusable(false);
		backButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen();
				((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).updateMessages();
			}
		});
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(Color.BLACK);
		backButton.setLocation(155, 408);
		backButton.setSize(190, 35);
		card.add(backButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(86, 68, 335, 268);
		card.add(scrollPane);
		
		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFocusable(false);
		list.setBackground(Color.BLACK);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		        	goToGameProfile();
		        }
		    }
		});
		scrollPane.setViewportView(list);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}


	
	public void populateList(){
		try {
			games = JDBCGameManager.listGames();
			model = new DefaultListModel<String>();
			listRowToGame = new TreeMap<String, Game>();
			for(Game g : games){
				String row;
				row = g.getGameTitle();
				model.addElement(row);
				listRowToGame.put(g.getGameTitle(), g);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | SQLException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare i giochi: database offline.");
				ScreenController.setPreviousScreen();
			}else if(e instanceof SQLException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare i giochi: problemi con il database.");
				ScreenController.setPreviousScreen();
			}
		}
	}
	
	private void goToGameProfile(){
		ScreenController.setScreen("GAMEPROFILESCREEN");
		((GameProfileView) ScreenController.getLoadedScreens().get("GAMEPROFILESCREEN")).populateFields(listRowToGame.get(list.getSelectedValue()));
	}
}
