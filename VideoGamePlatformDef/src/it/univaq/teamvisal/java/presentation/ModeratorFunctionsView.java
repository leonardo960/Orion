package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * ScreenView that serves as a menu for moderator to choose which functionality they
 * wish to manage.
 * @author Leonardo Formichetti
 *
 */
public class ModeratorFunctionsView extends ScreenViewSuper implements ScreenView {

	public ModeratorFunctionsView(){
		screenName = "MODERATORFUNCTIONSSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card.setLayout(null);
		
		JButton handleUsers = new JButton("Gestisci Utenza");
		handleUsers.setFocusable(false);
		handleUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setScreen("USERMANAGEMENTSCREEN");
			}
		});
		handleUsers.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		handleUsers.setBackground(Color.BLACK);
		handleUsers.setForeground(Color.WHITE);
		handleUsers.setBounds(149, 187, 190, 53);
		card.add(handleUsers);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.setFocusable(false);
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen();
				((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).updateMessages();
			}
		});
		btnIndietro.setForeground(Color.WHITE);
		btnIndietro.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnIndietro.setBackground(Color.BLACK);
		btnIndietro.setBounds(149, 315, 190, 53);
		card.add(btnIndietro);
		
		JButton btnGestisciRecensioni = new JButton("Gestisci Recensioni");
		btnGestisciRecensioni.setFocusable(false);
		btnGestisciRecensioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setScreen("REVIEWMANAGEMENTSCREEN");
				((ReviewManagementView) ScreenController.getLoadedScreens().get("REVIEWMANAGEMENTSCREEN")).populateList();
			}
		});
		btnGestisciRecensioni.setForeground(Color.WHITE);
		btnGestisciRecensioni.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnGestisciRecensioni.setBackground(Color.BLACK);
		btnGestisciRecensioni.setBounds(149, 251, 190, 53);
		card.add(btnGestisciRecensioni);
		
		JLabel moderatorPanelLabel = new JLabel("Pannello Moderatore");
		moderatorPanelLabel.setBackground(Color.BLACK);
		moderatorPanelLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		moderatorPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moderatorPanelLabel.setForeground(Color.WHITE);
		moderatorPanelLabel.setBounds(119, 78, 253, 76);
		card.add(moderatorPanelLabel);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		
		return card;
	}



}
