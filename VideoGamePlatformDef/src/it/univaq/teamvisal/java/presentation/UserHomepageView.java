package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.LogoutController;
import it.univaq.teamvisal.java.business.impl.ScreenController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UserHomepageView extends ScreenViewSuper implements ScreenView {
	
	private LogoutController logoutController;
	private JLabel welcomeLabel;
	private JButton modRequestButton;
	private JButton modFunctionsButton;
	public UserHomepageView(){
		screenName = "USERHOMEPAGESCREEN";
		logoutController = new LogoutController();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel initialize() {
		JPanel card = new JPanel();
		card.setLayout(null);
		
		welcomeLabel = new JLabel("Benvenuto " + JDBCUserManager.getCurrentUser().getUsername() + "!");
		welcomeLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setBounds(20, 45, 450, 48);
		card.add(welcomeLabel);
		
		JButton gamesButton = new JButton("Giochi");
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
					ScreenController.setPreviousScreen(screenName);
					logoutController.logout();
					((LoginScreenView) ScreenController.getLoadedScreens().get("LOGINSCREEN")).clearTextFields();
					
				} catch (DatabaseConnectionException | SQLException e1) {
					if(JOptionPane.showConfirmDialog(card, "Procedura di logout fallita: database offline.\nSi desidera uscire comunque? (I progressi nei vari giochi non saranno salvati)") == JOptionPane.YES_OPTION){
						System.exit(0);
					}else if(e1 instanceof SQLException){
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
		
		JLabel background = new JLabel("New label");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\workspace\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}

	@Override
	protected void clearTextFields() {
		//NEVER CALLED
	}
	
	public void setRequestButton(boolean value){
		modRequestButton.setEnabled(value);
		modRequestButton.setVisible(value);
	}
	public void setModeratorFunctionsButton(boolean value){
		modFunctionsButton.setEnabled(value);
		modFunctionsButton.setVisible(value);
	}
	public void updateUser(){
		welcomeLabel.setText("Benvenuto " + JDBCUserManager.getCurrentUser().getUsername());
	}
}
