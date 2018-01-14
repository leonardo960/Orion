package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.univaq.teamvisal.java.business.impl.LogoutController;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Message;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;


/**
 * First ScreenView the User sees when he/she logs in the system. There's a menu with
 * several options which link to other menus/functionalities.
 * @author Leonardo Formichetti
 *
 */
public class UserHomepageView extends ScreenViewSuper implements ScreenView {
	private LogoutController logoutController;
	private JLabel welcomeLabel;
	private JButton modRequestButton;
	private JButton modFunctionsButton;
	private JButton checkForMessages;
	private List<Message> messages;
	
	public UserHomepageView(){
		screenName = "USERHOMEPAGESCREEN";
		logoutController = new LogoutController();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel initialize() {
		card.setLayout(null);
		
		welcomeLabel = new JLabel("Benvenuto " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername() + "!");
		welcomeLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setBounds(20, 45, 450, 48);
		card.add(welcomeLabel);
		
		JButton gamesButton = new JButton("Giochi");
		gamesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setScreen("GAMESELECTIONSCREEN");
				((GameSelectionView) ScreenController.getLoadedScreens().get("GAMESELECTIONSCREEN")).populateList();
			}
		});
		gamesButton.setBackground(Color.BLACK);
		gamesButton.setForeground(Color.WHITE);
		gamesButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		gamesButton.setFocusable(false);
		gamesButton.setBounds(143, 155, 214, 42);
		card.add(gamesButton);
		
		modRequestButton = new JButton("Richiesta Moderatore");
		modRequestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					ScreenController.setScreen("MODERATORREGISTRATIONSCREEN");
				}
		});
		modRequestButton.setBackground(Color.BLACK);
		modRequestButton.setForeground(Color.WHITE);
		modRequestButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		modRequestButton.setFocusable(false);
		modRequestButton.setBounds(143, 296, 214, 42);
		card.add(modRequestButton);
		
		JButton profileButton = new JButton("Visualizza profilo");
		profileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setScreen("USERPROFILESCREEN");
				((UserProfileView) ScreenController.getLoadedScreens().get("USERPROFILESCREEN")).setTrophyDates();
				((UserProfileView) ScreenController.getLoadedScreens().get("USERPROFILESCREEN")).setProfileInformation();
			}
		});
		profileButton.setBackground(Color.BLACK);
		profileButton.setForeground(Color.WHITE);
		profileButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		profileButton.setFocusable(false);
		profileButton.setBounds(143, 226, 214, 42);
		card.add(profileButton);
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ScreenController.setPreviousScreen();
					logoutController.logout();
					((LoginScreenView) ScreenController.getLoadedScreens().get("LOGINSCREEN")).clearTextFields();
					
				} catch (DatabaseConnectionException | QueryException e1) {
					if(JOptionPane.showConfirmDialog(card, "Procedura di logout fallita: database offline.\nSi desidera uscire comunque? (I progressi nei vari giochi non saranno salvati)") == JOptionPane.YES_OPTION){
						System.exit(0);
					}else if(e1 instanceof QueryException){
						if(JOptionPane.showConfirmDialog(card, "Procedura di logout fallita a causa di problemi con il database.\nSi desidera uscire comunque? (I progressi nei vari giochi non saranno salvati)") == JOptionPane.YES_OPTION){
							System.exit(0);
						}
					}
				}
			}
		});
		logoutButton.setBackground(Color.BLACK);
		logoutButton.setFocusable(false);
		logoutButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setBounds(401, 11, 89, 23);
		card.add(logoutButton);
		
		modFunctionsButton = new JButton("Funzioni moderatore");
		modFunctionsButton.setVisible(false);
		modFunctionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setScreen("MODERATORFUNCTIONSSCREEN");
			}
		});
		modFunctionsButton.setEnabled(false);
		modFunctionsButton.setForeground(Color.WHITE);
		modFunctionsButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		modFunctionsButton.setFocusable(false);
		modFunctionsButton.setBackground(Color.BLACK);
		modFunctionsButton.setBounds(143, 296, 214, 42);
		card.add(modFunctionsButton);
		
		checkForMessages = new JButton("Messaggi");
		checkForMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayMessages();
			}
		});
		checkForMessages.setFocusable(false);
		checkForMessages.setBackground(Color.BLACK);
		checkForMessages.setForeground(Color.WHITE);
		checkForMessages.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		checkForMessages.setBounds(27, 370, 137, 81);
		card.add(checkForMessages);
		
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}
	
	/**
	 * Enables/Disables the button to send a moderator Request
	 * @param value
	 */
	public void setRequestButton(boolean value){
		modRequestButton.setEnabled(value);
		modRequestButton.setVisible(value);
	}
	
	/**
	 * Enables/Disables the button to access the moderator panel
	 * @param value
	 */
	public void setModeratorFunctionsButton(boolean value){
		modFunctionsButton.setEnabled(value);
		modFunctionsButton.setVisible(value);
	}
	/**
	 * Updates the welcome message with the appropriate currentUser name
	 */
	public void updateUser(){
		welcomeLabel.setText("Benvenuto " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername());
	}
	
	/**
	 * Checks if there's any Message waiting to be read by the currentUser
	 */
	public void updateMessages(){
		try {
			messages = MysqlDAOFactory.getInstance().getMysqlMessageManager().checkForMessages();
			checkForMessages.setText("Messaggi" + "(" + messages.size() + ")");
		} catch (DatabaseConnectionException | QueryException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare i messaggi: database offline.");
			}else if(e instanceof QueryException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare i messaggi: problemi con il database.");
			}
		}
	}
	
	/**
	 * Initiates a procedure which displays all pending messages for the User in succession
	 */
	private void displayMessages(){
	if(messages.size() > 0){
		for(Message message : messages){
			JTextArea msg = new JTextArea(message.getText());
        	
        	UIManager.put("OptionPane.minimumSize", new Dimension(500,300));
        	msg.setLineWrap(true);
        	msg.setWrapStyleWord(true);
        	JScrollPane scrollPane = new JScrollPane(msg);
        	JOptionPane.showMessageDialog(card, scrollPane, "Messaggio da parte di: " + message.getSender(), JOptionPane.INFORMATION_MESSAGE);
        	UIManager.put("OptionPane.minimumSize", new Dimension(550,50));
        	
        	try {
        		 MysqlDAOFactory.getInstance().getMysqlMessageManager().deleteMessage(message.getID());
			} catch (DatabaseConnectionException | QueryException e) {
				if(e instanceof DatabaseConnectionException){
					JOptionPane.showMessageDialog(card, "Impossibile portare a termina la procedura di visualizzazione del messaggio: database offline.");
					return;
				}else if(e instanceof QueryException){
					JOptionPane.showMessageDialog(card, "Impossibile portare a termina la procedura di visualizzazione del messaggio: problemi con il database.");
					return;
				}
			}
		}
		messages.clear();
		checkForMessages.setText("Messaggi(0)");
	}else{
		JOptionPane.showMessageDialog(card, "Nessun messaggio da visualizzare.");
		}
	}
}
