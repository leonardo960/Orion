package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCMessageManager;
import it.univaq.teamvisal.java.business.impl.JDBCReviewManager;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.model.Message;
import it.univaq.teamvisal.java.business.model.Review;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReviewManagementView extends ScreenViewSuper implements ScreenView {
	
	private JList<String> list;
	private List<Review> reviews;
	private TreeMap<Integer, Review> listRowToReview;
	private DefaultListModel<String> model;
	private JPanel card;
	public ReviewManagementView(){
		screenName = "REVIEWMANAGEMENTSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		card = new JPanel();
		card.setLayout(null);
		
		JLabel title = new JLabel("Recensioni in esame");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		title.setBounds(0, 24, 283, 92);
		card.add(title);
		
		list = new JList<String>();
		list.setBackground(Color.BLACK);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 10));
		list.setBounds(43, 101, 306, 341);
		
		card.add(list);
		
		JButton btnApprova = new JButton("Approva");
		/*btnApprova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(card, "Per favore, seleziona prima la recensione nella lista.");
			}else{
				try {
					Review review = new Review();
					JDBCReviewManager.manageReview(review, true);
					
					JOptionPane.showMessageDialog(card, "Richiesta approvata con successo.");
					JDBCMessageManager.postMessage(new Message("La recensione che avevi scritto per " + review.getGame().getGameTitle() + " è stata approvata!", "Moderatore: " + JDBCUserManager.getCurrentUser().getUsername(), ));
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
			}
		  });*/
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
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setPreviousScreen(screenName);
			}
		});
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
		try {
			reviews = JDBCReviewManager.getPendingReviews();
			listRowToReview = new TreeMap<Integer, Review>();
			model = new DefaultListModel<String>();
			int i = 0;
			for(Review r : reviews){
				String row;
				row = r.getUsername() + " - " + r.getGamename() + " - Voto: " + r.getVote() + " - " + "\"" + r.getText().substring(0, 20) + "...\"";
				model.addElement(row);
				listRowToReview.put(i++, r);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | SQLException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le recensione in esame: database offline.");
				ScreenController.setPreviousScreen(screenName);
			}else if(e instanceof SQLException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le recensioni in esame: problemi con il database.");
				ScreenController.setPreviousScreen(screenName);
			}
		}
	}
}
