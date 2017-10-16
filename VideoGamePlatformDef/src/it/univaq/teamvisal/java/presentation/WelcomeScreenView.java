package it.univaq.teamvisal.java.presentation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class WelcomeScreenView extends ScreenViewSuper implements ScreenView {

	
	
	public WelcomeScreenView(){
		screenName = "WELCOMESCREEN";
	}
	
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel initialize(){
		
		
		JPanel card = new JPanel();
		JButton userRegistrationButton = new JButton("Registrazione Utente");
		userRegistrationButton.setForeground(Color.WHITE);
		userRegistrationButton.setFocusable(false);
		userRegistrationButton.setBackground(Color.BLACK);
		userRegistrationButton.setBounds(149, 231, 191, 23);
		
			userRegistrationButton.addActionListener(new ActionListener()
			{
				  public void actionPerformed(ActionEvent e)
				  {
				   ScreenController.setScreen("USERREGISTRATIONSCREEN");
				  }
			});
			card.setLayout(null);
			JLabel title = new JLabel("Orion");
			title.setForeground(Color.WHITE);
			title.setHorizontalAlignment(SwingConstants.CENTER);
			title.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
			title.setBounds(141, 83, 214, 79);
			title.setAlignmentX(Component.CENTER_ALIGNMENT);
			card.add(title);
			JButton loginButton = new JButton("Login");
			loginButton.setForeground(Color.WHITE);
			loginButton.setFocusable(false);
			loginButton.setBackground(Color.BLACK);
			loginButton.setBounds(149, 197, 191, 23);
			loginButton.addActionListener(new ActionListener()
			{
				  public void actionPerformed(ActionEvent e)
				  {
				   ScreenController.setScreen("LOGINSCREEN");
				  }
			});
			
			loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			card.add(loginButton);
			userRegistrationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			card.add(userRegistrationButton);
		
		JButton infoButton = new JButton("Info");
		infoButton.setForeground(Color.WHITE);
		infoButton.setFocusable(false);
		infoButton.setBackground(Color.BLACK);
		infoButton.setBounds(149, 265, 191, 23);
		
		
		
		
		
		infoButton.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
			   JOptionPane.showMessageDialog(
					   null, 
					   "Piattaforma creata dal Team Visal. Versione 1.1",
			   	        "Info",
			   	        JOptionPane.INFORMATION_MESSAGE
			   	       );
			  }
			  
		});
		infoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		card.add(infoButton);
		
		JLabel background = new JLabel("");
		background.setBackground(Color.BLACK);
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
	
		
		return card;
		
		
	}



	@Override
	protected void clearTextFields() {
		// TODO Auto-generated method stub
		//THIS METHOD WILL NEVER BE CALLED HERE FOR THE LACK OF JTEXTCOMPONENTS
	}
}
