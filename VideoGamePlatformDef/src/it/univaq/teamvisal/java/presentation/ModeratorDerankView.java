package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCMessageManager;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.model.Message;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class ModeratorDerankView extends ScreenViewSuper implements ScreenView {
	
	private JList<String> list;
	private DefaultListModel model;
	
	private JPanel card;
	public ModeratorDerankView(){
		screenName = "MODERATORDERANKSCREEN";
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card = new JPanel();
		card.setLayout(null);
		
		list = new JList();
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
		derank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(card, "Per favore, seleziona prima l'utente nella lista.");
				}else{
					try {
						JDBCUserManager.derankModerator(list.getSelectedValue().substring(0, list.getSelectedValue().indexOf(" ")));
						JOptionPane.showMessageDialog(card, "L'utente selezionato è stato degradato correttamente.");
						JDBCMessageManager.postMessage(new Message("Siamo spiacenti di comunicarti che ti è stato revocato lo status di Moderatore. Potrai fare richiesta per essere riammesso, se lo desideri.", "Moderatore: " + JDBCUserManager.getCurrentUser().getUsername(), list.getSelectedValue().substring(0, list.getSelectedValue().indexOf(" "))));
						model.remove(list.getSelectedIndex());
					} catch (DatabaseConnectionException | SQLException e1) {
						if(e1 instanceof DatabaseConnectionException){
							JOptionPane.showMessageDialog(card, "Impossibile degradare il moderatore selezionato: database offline.");
						}else if(e1 instanceof SQLException){
							JOptionPane.showMessageDialog(card, "Impossibile degradare il moderatore selezionato: problemi con il database.");
						}
					}
				}
			}
		});
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
		try {
			List<String> stringList = JDBCUserManager.getModerators();
			stringList.remove(JDBCUserManager.getCurrentUser().getUsername() + " - " + JDBCUserManager.getCurrentUser().getNome() + " " + JDBCUserManager.getCurrentUser().getCognome());
			model = new DefaultListModel();
			for(String s : stringList){
				model.addElement(s);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | SQLException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare la lista dei moderatori: database offline.");
				ScreenController.setPreviousScreen(screenName);
			}else if(e instanceof SQLException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare la lista dei moderatori: problemi con il database.");
				ScreenController.setPreviousScreen(screenName);
			}
		}
		
	}
}
