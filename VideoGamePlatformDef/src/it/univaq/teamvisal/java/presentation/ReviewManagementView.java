package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class ReviewManagementView extends ScreenViewSuper implements ScreenView {
	
	public ReviewManagementView(){
		screenName = "REVIEWMANAGEMENTSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		JPanel card = new JPanel();
		card.setLayout(null);
		
		JLabel title = new JLabel("Recensioni in esame");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		title.setBounds(0, 24, 283, 92);
		card.add(title);
		
		JList list = new JList();
		list.setBackground(Color.BLACK);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		list.setBounds(43, 101, 306, 341);
		card.add(list);
		
		JButton btnApprova = new JButton("Approva");
		btnApprova.setBackground(Color.BLACK);
		btnApprova.setForeground(Color.WHITE);
		btnApprova.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnApprova.setBounds(359, 129, 116, 36);
		card.add(btnApprova);
		
		JButton btnRespingi = new JButton("Respingi");
		btnRespingi.setForeground(Color.WHITE);
		btnRespingi.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnRespingi.setBackground(Color.BLACK);
		btnRespingi.setBounds(359, 180, 116, 36);
		card.add(btnRespingi);
		
		JButton back = new JButton("Indietro");
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setBackground(Color.BLACK);
		back.setBounds(10, 453, 116, 36);
		card.add(back);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		return card;
	}

	@Override
	protected void clearTextFields() {
	
	}
	
	public void populateList(){
		/*try {
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
		}*/
	}
}
