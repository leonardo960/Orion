package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.ScreenController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModeratorDerankView extends ScreenViewSuper implements ScreenView {
	
	public ModeratorDerankView(){
		screenName = "MODERATORDERANKSCREEN";
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		JPanel card = new JPanel();
		card.setLayout(null);
		
		JList list = new JList();
		list.setBackground(Color.BLACK);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(96, 122, 320, 247);
		card.add(list);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen(screenName);
			}
		});
		back.setBackground(Color.BLACK);
		back.setFocusable(false);
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setBounds(137, 410, 114, 41);
		card.add(back);
		
		JLabel derankModerators = new JLabel("Degrada Moderatori");
		derankModerators.setHorizontalAlignment(SwingConstants.CENTER);
		derankModerators.setForeground(Color.WHITE);
		derankModerators.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		derankModerators.setBounds(149, 57, 221, 56);
		card.add(derankModerators);
		
		JButton derank = new JButton("Degrada");
		derank.setForeground(Color.WHITE);
		derank.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		derank.setFocusable(false);
		derank.setBackground(Color.BLACK);
		derank.setBounds(274, 410, 114, 41);
		card.add(derank);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}

	@Override
	protected void clearTextFields() {
		// TODO Auto-generated method stub
		
	}
	
	public void populateList(){
		
	}
}
