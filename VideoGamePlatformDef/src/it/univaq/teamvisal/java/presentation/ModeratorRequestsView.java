package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModeratorRequestsView extends ScreenViewSuper implements ScreenView {
	private JList<String> list;
	private JPanel card;
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
		card = new JPanel();
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
		        JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		        	JTextArea msg = new JTextArea(modRequests.get(list.getSelectedValue()));
		        	msg.setBounds(msg.getX(), msg.getY(), 200, 100);
		        	msg.setLineWrap(true);
		        	msg.setWrapStyleWord(true);
		        	JScrollPane scrollPane = new JScrollPane(msg);
		        	JOptionPane.showMessageDialog(card, scrollPane, "Pitch della richiesta", JOptionPane.INFORMATION_MESSAGE);
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
				try {
					JDBCUserManager.manageRequest(list.getSelectedValue(), true);
					JOptionPane.showMessageDialog(card, "Richiesta approvata con successo.");
					model.remove(list.getSelectedIndex());
				} catch (DatabaseConnectionException | SQLException e1) {
					if(e1 instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile approvare la richiesta: database offline.");
						ScreenController.setPreviousScreen(screenName);
					}else if(e1 instanceof SQLException){
						JOptionPane.showMessageDialog(card, "Impossibile approvare la richiesta: problemi con il database.");
						ScreenController.setPreviousScreen(screenName);
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
				try {
					JDBCUserManager.manageRequest(list.getSelectedValue(), false);
					model.remove(list.getSelectedIndex());
					JOptionPane.showMessageDialog(card, "Richiesta respinta con successo.");
				} catch (DatabaseConnectionException | SQLException e1) {
					if(e1 instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile respingere la richiesta: database offline.");
						ScreenController.setPreviousScreen(screenName);
					}else if(e1 instanceof SQLException){
						JOptionPane.showMessageDialog(card, "Impossibile respingere la richiesta: problemi con il database.");
						ScreenController.setPreviousScreen(screenName);
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
				ScreenController.setPreviousScreen(screenName);
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setBounds(328, 388, 135, 44);
		card.add(back);
		
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
			modRequests = JDBCUserManager.getModeratorRequests();
			model = new DefaultListModel<String>();
			for(String s : modRequests.keySet()){
				model.addElement(s);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | SQLException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le richieste moderatore: database offline.");
				ScreenController.setPreviousScreen(screenName);
			}else if(e instanceof SQLException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le richieste moderatore: problemi con il database.");
				ScreenController.setPreviousScreen(screenName);
			}
		}
	}
}
