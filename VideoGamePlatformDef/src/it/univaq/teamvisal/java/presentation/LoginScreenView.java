package it.univaq.teamvisal.java.presentation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.NoUserException;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.LoginController;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class LoginScreenView extends ScreenViewSuper implements ScreenView {
	private JPasswordField passwordField;
	private LoginController loginController;
	private JTextField usernameField;
	 
	public LoginScreenView(){
		screenName = "LOGINSCREEN";
		loginController = new LoginController();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel initialize() {	
		usernameField = new JTextField("");
		usernameField.setBounds(167, 176, 200, 30);
		JButton loginButton = new JButton("Autenticati");
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.BLACK);
		loginButton.setFocusable(false);
		loginButton.setBounds(216, 258, 98, 23);
		JButton backButton = new JButton("Indietro");
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(Color.BLACK);
		backButton.setFocusable(false);
		backButton.setBounds(216, 292, 98, 23);
		JLabel title = new JLabel("Procedura di autenticazione");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(155, 91, 220, 41);
		usernameField.setMaximumSize(new Dimension(200, 30));
		
		
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				clearTextFields();
				ScreenController.setPreviousScreen(screenName);
			}
		});
		
		usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		card.setLayout(null);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		card.add(title);
		card.add(usernameField);
		card.add(loginButton);
		card.add(backButton);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(167, 217, 200, 30);
		card.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(82, 176, 75, 30);
		card.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(82, 217, 75, 30);
		card.add(lblPassword);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\bg.jpg"));
		background.setBackground(Color.BLACK);
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(   !usernameField.getText().contains(" ")
				   && usernameField.getText().length() > 0 
				   && !String.copyValueOf(passwordField.getPassword()).contains(" ")
				   && passwordField.getPassword().length > 0){
					try {
						loginController.login(usernameField.getText(), String.copyValueOf(passwordField.getPassword()));
						ScreenController.setScreen("USERHOMEPAGESCREEN");
						((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).updateUser();
						if(JDBCUserManager.getCurrentUser().getType().equals("B")){
							((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).setModeratorFunctionsButton(false);
							if(JDBCUserManager.getCurrentUser().isRequestSent()){
								((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).setRequestButton(false);
							}else{
								((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).setRequestButton(true);
							}
						}else{
							((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).setRequestButton(false);
							((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).setModeratorFunctionsButton(true);
						}
						((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).updateMessages();
						
					} catch (DatabaseConnectionException | SQLException | NoUserException e) {
						if(e instanceof DatabaseConnectionException){
							JOptionPane.showMessageDialog(card, "Login fallito: problemi con il database.");
							clearTextFields();
							ScreenController.setPreviousScreen(screenName);
						}else if(e instanceof SQLException){
							JOptionPane.showMessageDialog(card, "Login fallito: problemi con il database.");
							clearTextFields();
							ScreenController.setPreviousScreen(screenName);
						}else if(e instanceof NoUserException){
							JOptionPane.showMessageDialog(card, "Login fallito: lo username o la password sono errati.");
						}
					}
				}else{
					JOptionPane.showMessageDialog(card, "Per favore, inserisci sia username che password e assicurati che non ci siano spazi!");
				}
				
			}
		});
		
		return card;
	}

	
	public void clearTextFields() {
		passwordField.setText("");
		usernameField.setText("");
	}
}
