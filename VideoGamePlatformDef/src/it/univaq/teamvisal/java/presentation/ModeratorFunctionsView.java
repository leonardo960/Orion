package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.ScreenController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModeratorFunctionsView extends ScreenViewSuper implements ScreenView {

	public ModeratorFunctionsView(){
		screenName = "MODERATORFUNCTIONSSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		JPanel card = new JPanel();
		card.setLayout(null);
		
		JButton handleUsers = new JButton("Gestisci Utenza");
		handleUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		handleUsers.setFocusable(false);
		handleUsers.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		handleUsers.setBackground(Color.BLACK);
		handleUsers.setForeground(Color.WHITE);
		handleUsers.setBounds(149, 187, 190, 53);
		card.add(handleUsers);
		
		JButton btnIndietro = new JButton("Indietro");
		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen(screenName);
			}
		});
		btnIndietro.setFocusable(false);
		btnIndietro.setForeground(Color.WHITE);
		btnIndietro.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnIndietro.setBackground(Color.BLACK);
		btnIndietro.setBounds(149, 315, 190, 53);
		card.add(btnIndietro);
		
		JButton btnGestisciRecensioni = new JButton("Gestisci Recensioni");
		btnGestisciRecensioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnGestisciRecensioni.setFocusable(false);
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
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\workspace\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		
		return card;
	}

	@Override
	protected void clearTextFields() {
		
		
	}

}
