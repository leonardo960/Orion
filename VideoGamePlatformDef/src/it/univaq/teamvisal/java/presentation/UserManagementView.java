package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * ScreenView to display a menu for all functionalities related to User management for
 * moderators
 * @author Leonardo Formichetti
 *
 */
public class UserManagementView extends ScreenViewSuper implements ScreenView {
	
	public UserManagementView(){
		screenName = "USERMANAGEMENTSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card.setLayout(null);
		
		JButton demote = new JButton("Degrada Moderatori");
		demote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setScreen("MODERATORDERANKSCREEN");
				((ModeratorDerankView) ScreenController.getLoadedScreens().get("MODERATORDERANKSCREEN")).populateList();
			}
		});
		demote.setFocusable(false);
		demote.setBackground(Color.BLACK);
		demote.setForeground(Color.WHITE);
		demote.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		demote.setBounds(120, 158, 286, 54);
		card.add(demote);
		
		JButton requests = new JButton("Gestisci Richieste Moderatore");
		requests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setScreen("MODERATORREQUESTSSCREEN");
				((ModeratorRequestsView) ScreenController.getLoadedScreens().get("MODERATORREQUESTSSCREEN")).populateList();
			}
		});
		requests.setForeground(Color.WHITE);
		requests.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		requests.setFocusable(false);
		requests.setBackground(Color.BLACK);
		requests.setBounds(120, 223, 286, 54);
		card.add(requests);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setPreviousScreen();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setBounds(120, 288, 286, 54);
		card.add(back);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}


}
