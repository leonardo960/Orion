package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.univaq.teamvisal.java.business.impl.JDBCMessageManager;
import it.univaq.teamvisal.java.business.impl.JDBCReviewManager;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Message;
import it.univaq.teamvisal.java.business.model.Review;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import java.util.TreeMap;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

/**
 * ScreenView for moderators to manage eventual currently pending game reviews. They are
 * displayed in a List fashion and two buttons are provided to approve or reject them.
 * @author Leonardo Formichetti
 *
 */
public class ReviewManagementView extends ScreenViewSuper implements ScreenView {
	
	private JList<String> list;
	private List<Review> reviews;
	private DefaultListModel<String> model;
	public ReviewManagementView(){
		screenName = "REVIEWMANAGEMENTSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
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
		list.setBounds(10, 106, 354, 284);
		
		card.add(list);
		
		JButton btnApprova = new JButton("Approva");
		btnApprova.setFocusable(false);
		btnApprova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(card, "Per favore, seleziona prima la recensione nella lista.");
			}else{
				try {
					JDBCReviewManager.manageReview(getReviewFromList(list.getSelectedValue()), true);
					JOptionPane.showMessageDialog(card, "Recensione approvata con successo.");
					JDBCMessageManager.postMessage(new Message("La recensione che avevi scritto per " + getReviewFromList(list.getSelectedValue()).getGamename() + " è stata approvata!", "Moderatore: " + JDBCUserManager.getCurrentUser().getUsername(), getReviewFromList(list.getSelectedValue()).getUsername()));
					model.remove(list.getSelectedIndex());
				} catch (DatabaseConnectionException | SQLException e1) {
					if(e1 instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile approvare la richiesta: database offline.");
						ScreenController.setPreviousScreen();
					}else if(e1 instanceof SQLException){
						JOptionPane.showMessageDialog(card, "Impossibile approvare la richiesta: problemi con il database.");
						ScreenController.setPreviousScreen();
						}
					}
				}
			}
		  });
		btnApprova.setBackground(Color.BLACK);
		btnApprova.setForeground(Color.WHITE);
		btnApprova.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnApprova.setBounds(374, 129, 116, 36);
		card.add(btnApprova);
		
		JButton btnRespingi = new JButton("Respingi");
		btnRespingi.setFocusable(false);
		btnRespingi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.isSelectionEmpty()){
					JOptionPane.showMessageDialog(card, "Per favore, seleziona prima la recensione nella lista.");
			}else{
				try {
					JDBCReviewManager.manageReview(getReviewFromList(list.getSelectedValue()), false);
					JOptionPane.showMessageDialog(card, "Recensione respinta con successo.");
					JDBCMessageManager.postMessage(new Message("La recensione che avevi scritto per " + getReviewFromList(list.getSelectedValue()).getGamename() + " è stata respinta. Attieniti più alle linee guida in futuro.", "Moderatore: " + JDBCUserManager.getCurrentUser().getUsername(), getReviewFromList(list.getSelectedValue()).getUsername()));
					model.remove(list.getSelectedIndex());
				} catch (DatabaseConnectionException | SQLException e1) {
					if(e1 instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile respingere la richiesta: database offline.");
						ScreenController.setPreviousScreen();
					}else if(e1 instanceof SQLException){
						JOptionPane.showMessageDialog(card, "Impossibile respingere la richiesta: problemi con il database.");
						ScreenController.setPreviousScreen();
						}
					}
				}
			}
		});
		btnRespingi.setForeground(Color.WHITE);
		btnRespingi.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		btnRespingi.setBackground(Color.BLACK);
		btnRespingi.setBounds(374, 176, 116, 36);
		card.add(btnRespingi);
		
		JButton back = new JButton("Indietro");
		back.setFocusable(false);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setPreviousScreen();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setBackground(Color.BLACK);
		back.setBounds(37, 412, 116, 36);
		card.add(back);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        @SuppressWarnings("unchecked")
				JList<String> list = (JList<String>)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            // Double-click detected
		        	JTextArea msg = new JTextArea(getReviewFromList(list.getSelectedValue()).getText());
		        	UIManager.put("OptionPane.minimumSize", new Dimension(500,300));
		        	msg.setLineWrap(true);
		        	msg.setWrapStyleWord(true);
		        	JScrollPane scrollPane = new JScrollPane(msg);
		        	JOptionPane.showMessageDialog(card, scrollPane, "Utente: " + getReviewFromList(list.getSelectedValue()).getUsername() + "   " + "Gioco: " +  getReviewFromList(list.getSelectedValue()).getGamename() + "   " + "Voto:" + getReviewFromList(list.getSelectedValue()).getVote() + "/5", JOptionPane.INFORMATION_MESSAGE);
		        	UIManager.put("OptionPane.minimumSize", new Dimension(550,50));
		        }
		    }
		});
		
		return card;
	}


	/**
	 * Populates the list of reviews asking the Review DAO to return a List of them.
	 * This method is called right after the Screen is switched.
	 */
	public void populateList(){
		try {
			reviews = JDBCReviewManager.getPendingReviews();
			model = new DefaultListModel<String>();
			for(Review r : reviews){
				String row;
				row = "Utente: " + r.getUsername() + "   " + "Gioco: " +  r.getGamename() + "   " + "Voto:" + r.getVote() + "/5";
				model.addElement(row);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | SQLException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le recensione in esame: database offline.");
				ScreenController.setPreviousScreen();
			}else if(e instanceof SQLException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le recensioni in esame: problemi con il database.");
				ScreenController.setPreviousScreen();
			}
		}
	}
	
	/**
	 * Convenience method to return a Review object given a String row from the JList
	 * of reviews.
	 * @param row
	 * @return
	 */
	private Review getReviewFromList(String row){
		for(Review r : reviews){
			if(row.contains(r.getGamename()) && row.contains(r.getUsername())){
				return r;
			}
		}
		return null;
	}
}
