package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Message;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * ScreenView for moderators to check eventual currently pending moderator requests. There
 * are two buttons to approve or reject requests and they are displayed in a List fashion
 * @author Leonardo Formichetti
 *
 */
public class ModeratorRequestsView extends ScreenViewSuper implements ScreenView {
	private JList<String> list;
	private TreeMap<String, String> modRequests;
	private DefaultListModel<String> model;
	
	public ModeratorRequestsView(){
		screenName = "MODERATORREQUESTSSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card.setLayout(null);
		
		list = new JList<String>();
		list.setBackground(Color.BLACK);
		list.setOpaque(false);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(33, 108, 235, 266);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        @SuppressWarnings("unchecked")
				JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		        	JTextArea msg = new JTextArea(modRequests.get(list.getSelectedValue()));
		        	UIManager.put("OptionPane.minimumSize", new Dimension(500,300));
		        	msg.setLineWrap(true);
		        	msg.setWrapStyleWord(true);
		        	JScrollPane scrollPane = new JScrollPane(msg);
		        	JOptionPane.showMessageDialog(card, scrollPane, "Pitch della richiesta", JOptionPane.INFORMATION_MESSAGE);
		        	UIManager.put("OptionPane.minimumSize", new Dimension(550,50));
		        }
		    }
		});
		
		card.add(list);
		
		JLabel modRequestsLabel = new JLabel("Richieste Moderatore in esame");
		modRequestsLabel.setForeground(Color.WHITE);
		modRequestsLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		modRequestsLabel.setBounds(33, 43, 285, 54);
		card.add(modRequestsLabel);
		
		JButton btnApprova = new JButton("Approva");
		btnApprova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(card, "Per favore, seleziona prima l'utente nella lista.");
			}else{
				try {
					MysqlDAOFactory.getInstance().getMysqlUserManager().manageRequest(list.getSelectedValue(), true);
					
					JOptionPane.showMessageDialog(card, "Richiesta approvata con successo.");
					MysqlDAOFactory.getInstance().getMysqlMessageManager().postMessage(new Message("Complimenti, la tua richiesta per diventare Moderatore è stata approvata! Da oggi in poi avrai accesso al Pannello Moderatore, dove potrai gestire l'utenza e moderare le recensioni. Benvenuto a bordo, e buon lavoro!", "Moderatore: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername(), list.getSelectedValue()));
					model.remove(list.getSelectedIndex());
				} catch (DatabaseConnectionException | QueryException e1) {
					if(e1 instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile approvare la richiesta: database offline.");
						ScreenController.setPreviousScreen();
					}else if(e1 instanceof QueryException){
						JOptionPane.showMessageDialog(card, "Impossibile approvare la richiesta: problemi con il database.");
						ScreenController.setPreviousScreen();
						}
					}
				}
			}
		});
		btnApprova.setFocusable(false);
		btnApprova.setBackground(Color.BLACK);
		btnApprova.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnApprova.setForeground(Color.WHITE);
		btnApprova.setBounds(328, 125, 135, 44);
		card.add(btnApprova);
		
		JButton btnRespingi = new JButton("Respingi");
		btnRespingi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(card, "Per favore, seleziona prima l'utente nella lista.");
			}else{
				try {
					MysqlDAOFactory.getInstance().getMysqlUserManager().manageRequest(list.getSelectedValue(), false);
					model.remove(list.getSelectedIndex());
					JOptionPane.showMessageDialog(card, "Richiesta respinta con successo.");
				} catch (DatabaseConnectionException | QueryException e1) {
					if(e1 instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile respingere la richiesta: database offline.");
						ScreenController.setPreviousScreen();
					}else if(e1 instanceof QueryException){
						JOptionPane.showMessageDialog(card, "Impossibile respingere la richiesta: problemi con il database.");
						ScreenController.setPreviousScreen();
						}
					}
				}
			}
		});
		btnRespingi.setForeground(Color.WHITE);
		btnRespingi.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnRespingi.setFocusable(false);
		btnRespingi.setBackground(Color.BLACK);
		btnRespingi.setBounds(328, 180, 135, 44);
		card.add(btnRespingi);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenController.setPreviousScreen();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setBounds(328, 388, 135, 44);
		card.add(back);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}

	
	/**
	 * Populates the list of moderator requests asking the User DAO to return a List of them.
	 * This method is called right after the Screen is switched.
	 */
	public void populateList(){
		try {
			modRequests = MysqlDAOFactory.getInstance().getMysqlUserManager().getModeratorRequests();
			model = new DefaultListModel<String>();
			for(String s : modRequests.keySet()){
				model.addElement(s);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | QueryException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le richieste moderatore: database offline.");
				ScreenController.setPreviousScreen();
			}else if(e instanceof QueryException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le richieste moderatore: problemi con il database.");
				ScreenController.setPreviousScreen();
			}
		}
	}
}
