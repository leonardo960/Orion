package it.univaq.teamvisal.java.presentation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.univaq.teamvisal.java.business.impl.RegistrationController;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.impl.exceptions.UserAlreadyExistsException;
import it.univaq.teamvisal.java.business.model.Message;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
/**
 * ScreenView for the User Registration Screen. Here the User can register a new account,
 * provided he doesn't use an already picked username. Neither the username nor the password
 * have any restrictions. Database update is immediate.
 * @author Leonardo Formichetti
 *
 */
public class UserRegistrationView extends ScreenViewSuper implements ScreenView {
	private JTextField name;
	private JTextField surname;
	private JTextField username;
	private JPasswordField password;
	private RegistrationController registrationController;
	
	public UserRegistrationView(){
		screenName = "USERREGISTRATIONSCREEN";
		registrationController = new RegistrationController();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel initialize() {
		JButton confirm = new JButton("Registrati");
		confirm.setForeground(Color.WHITE);
		
		confirm.setBackground(Color.BLACK);
		confirm.setFocusable(false);
		confirm.setBounds(218, 334, 100, 23);
		JButton back = new JButton("Indietro");
		back.setForeground(Color.WHITE);
		back.setBackground(Color.BLACK);
		back.setFocusable(false);
		back.setBounds(218, 368, 100, 23);
		username = new JTextField("");
		username.setBounds(170, 150, 200, 30);
		password = new JPasswordField("");
		password.setBounds(170, 191, 200, 30);
		JLabel title = new JLabel("Procedura registrazione utente");
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(137, 69, 265, 58);
		
		username.setMaximumSize(new Dimension(200, 30));
		password.setMaximumSize(new Dimension(200, 30));
		
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		password.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		name = new JTextField("");
		surname = new JTextField("");
		
		back.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  clearTextFields();
				  ScreenController.setPreviousScreen();
			  }
		});
		card.setLayout(null);
		card.add(title);
		card.add(username);
		card.add(password);
		card.add(confirm);
		card.add(back);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		usernameLabel.setBounds(75, 150, 85, 30);
		card.add(usernameLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(75, 191, 85, 30);
		card.add(lblPassword);
		
		
		name.setMaximumSize(new Dimension(200, 30));
		name.setAlignmentX(0.5f);
		name.setBounds(170, 232, 200, 30);
		card.add(name);
		
		
		surname.setMaximumSize(new Dimension(200, 30));
		surname.setAlignmentX(0.5f);
		surname.setBounds(170, 273, 200, 30);
		card.add(surname);
		
		JLabel nameLabel = new JLabel("Nome:");
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		nameLabel.setBounds(75, 232, 85, 30);
		card.add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Cognome:");
		surnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		surnameLabel.setForeground(Color.WHITE);
		surnameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		surnameLabel.setBounds(75, 273, 85, 30);
		card.add(surnameLabel);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		background.setBackground(Color.BLACK);
		card.add(background);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(username.getText().length() > 0 &&
				   password.getPassword().length > 0 &&
				   name.getText().length() > 0 &&
				   surname.getText().length() > 0 &&
				   !username.getText().contains(" ") &&
				   !(Arrays.asList(password.getPassword()).contains(" ")) &&
				   !name.getText().contains(" ") &&
				   !surname.getText().contains(" ")){
					try {
						if(registrationController.register(username.getText(), String.copyValueOf(password.getPassword()), name.getText(), surname.getText())){
							JOptionPane.showMessageDialog(card, "Registrazione avvenuta con successo!");
							MysqlDAOFactory.getInstance().getMysqlMessageManager().postMessage(new Message("Benvenuto su Orion! Messaggi come questo verrano usati per comunicarti ogni sorta di notifica. Buon Divertimento!", "Sistema", username.getText()));
							clearTextFields();
							ScreenController.setPreviousScreen();
					}} catch (HeadlessException | QueryException | UserAlreadyExistsException | DatabaseConnectionException e) {
						if(e instanceof QueryException){
							JOptionPane.showMessageDialog(card, "Registrazione fallita: problemi con il database.");
							clearTextFields();
							ScreenController.setPreviousScreen();
						}else if(e instanceof UserAlreadyExistsException){
							JOptionPane.showMessageDialog(card, "Registrazione fallita: username già in uso.");
							username.setText("");
						}else if(e instanceof DatabaseConnectionException){
							JOptionPane.showMessageDialog(card, "Registrazione fallita: database offline.");
							clearTextFields();
							ScreenController.setPreviousScreen();
						}
					}
				}else{
					JOptionPane.showMessageDialog(card, "Per favore, riempire tutti i campi e/o rimuovere eventuali spazi");
				}
			}
		});
		
		return card;
	}
	/**
	 * Clears the ScreenView's JTextComponents so that when the User returns to
	 * the ScreenView they are empty again and ready to be filled.
	 */
	protected void clearTextFields() {
		username.setText("");
		password.setText("");
		name.setText("");
		surname.setText("");
	}
}
