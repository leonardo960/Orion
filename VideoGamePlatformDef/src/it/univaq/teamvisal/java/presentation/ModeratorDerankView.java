package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Message;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

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
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * ScreenView for the derank functionality managed by moderators. It lists all the current
 * moderators in Orion with a button to confirm the derank.
 * @author Leonardo Formichetti
 *
 */
public class ModeratorDerankView extends ScreenViewSuper implements ScreenView {
	
	private JList<String> list;
	private DefaultListModel<String> model;
	
	public ModeratorDerankView(){
		screenName = "MODERATORDERANKSCREEN";
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card.setLayout(null);
		
		list = new JList<String>();
		list.setBackground(Color.BLACK);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(96, 122, 320, 247);
		card.add(list);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen();
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
						MysqlDAOFactory.getInstance().getMysqlUserManager().derankModerator(list.getSelectedValue().substring(0, list.getSelectedValue().indexOf(" ")));
						JOptionPane.showMessageDialog(card, "L'utente selezionato è stato degradato correttamente.");
						MysqlDAOFactory.getInstance().getMysqlMessageManager().postMessage(new Message("Siamo spiacenti di comunicarti che ti è stato revocato lo status di Moderatore. Potrai fare richiesta per essere riammesso, se lo desideri.", "Moderatore: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername(), list.getSelectedValue().substring(0, list.getSelectedValue().indexOf(" "))));
						model.remove(list.getSelectedIndex());
					} catch (DatabaseConnectionException | QueryException e1) {
						if(e1 instanceof DatabaseConnectionException){
							JOptionPane.showMessageDialog(card, "Impossibile degradare il moderatore selezionato: database offline.");
						}else if(e1 instanceof QueryException){
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
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}

	
	/**
	 * Populates the list of moderators asking the User DAO to return a List of them.
	 * This method is called right after the Screen is switched.
	 */
	public void populateList(){
		try {
			List<String> stringList = MysqlDAOFactory.getInstance().getMysqlUserManager().getModerators();
			stringList.remove(MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername() + " - " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getNome() + " " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getCognome());
			model = new DefaultListModel<String>();
			for(String s : stringList){
				model.addElement(s);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | QueryException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare la lista dei moderatori: database offline.");
				ScreenController.setPreviousScreen();
			}else if(e instanceof QueryException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare la lista dei moderatori: problemi con il database.");
				ScreenController.setPreviousScreen();
			}
		}
		
	}
}
