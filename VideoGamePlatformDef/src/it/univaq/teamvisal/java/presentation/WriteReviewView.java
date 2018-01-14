package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.business.model.Review;
import it.univaq.teamvisal.java.presentation.utilities.DocumentSizeFilter;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
/**
 * ScreenView for the Screen where a Review for a particular Game can be written
 * and sent by the User.
 * @author Leonardo Formichetti
 *
 */
public class WriteReviewView extends ScreenViewSuper implements ScreenView {
	private JTextArea txtrScriviLaTua;
	private JLabel wordCount;
	private JLabel title;
	private Game chosenGame;
	private JLabel vote;
	private JRadioButton radioButton, radioButton_1, radioButton_2, radioButton_3, radioButton_4;
	private JButton send;
	
	public WriteReviewView(){
		screenName = "WRITEREVIEWSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		JPanel card = new JPanel();
		card.setLayout(null);
		
		title = new JLabel("Scrivi recensione per");
		title.setBackground(Color.BLACK);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setBounds(27, 53, 382, 88);
		card.add(title);
		
		txtrScriviLaTua = new JTextArea();
		txtrScriviLaTua.setLineWrap(true);
		
		
	    AbstractDocument doc = (AbstractDocument) txtrScriviLaTua.getDocument();
	    doc.setDocumentFilter(new DocumentSizeFilter(600));
	    
		txtrScriviLaTua.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		txtrScriviLaTua.setText("Scrivi la tua recensione!");
		txtrScriviLaTua.setBounds(68, 152, 361, 207);
		card.add(txtrScriviLaTua);
		
		wordCount = new JLabel("25/600");
		wordCount.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		wordCount.setForeground(Color.WHITE);
		wordCount.setBounds(439, 336, 56, 26);
		card.add(wordCount);
		
		send = new JButton("Invia");
		send.setFocusable(false);
		send.setBackground(Color.BLACK);
		send.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		send.setForeground(Color.WHITE);
		send.setBounds(185, 402, 112, 23);
		card.add(send);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setPreviousScreen();
			}
		});
		back.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setForeground(Color.WHITE);
		back.setBounds(185, 436, 112, 23);
		card.add(back);
		
		vote = new JLabel("Voto:");
		vote.setHorizontalAlignment(SwingConstants.CENTER);
		vote.setForeground(Color.WHITE);
		vote.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		vote.setBounds(68, 370, 63, 26);
		card.add(vote);
		
		radioButton = new JRadioButton("1");
		radioButton.setFocusable(false);
		radioButton.setBackground(Color.BLACK);
		radioButton.setForeground(Color.WHITE);
		radioButton.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		radioButton.setBounds(137, 372, 35, 23);
		card.add(radioButton);
		
		radioButton_1 = new JRadioButton("2");
		radioButton_1.setFocusable(false);
		radioButton_1.setForeground(Color.WHITE);
		radioButton_1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		radioButton_1.setBackground(Color.BLACK);
		radioButton_1.setBounds(185, 372, 35, 23);
		card.add(radioButton_1);
		
		radioButton_2 = new JRadioButton("3");
		radioButton_2.setFocusable(false);
		radioButton_2.setForeground(Color.WHITE);
		radioButton_2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		radioButton_2.setBackground(Color.BLACK);
		radioButton_2.setBounds(233, 372, 35, 23);
		radioButton_2.setSelected(true);
		card.add(radioButton_2);
		
		radioButton_3 = new JRadioButton("4");
		radioButton_3.setFocusable(false);
		radioButton_3.setForeground(Color.WHITE);
		radioButton_3.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		radioButton_3.setBackground(Color.BLACK);
		radioButton_3.setBounds(281, 372, 35, 23);
		card.add(radioButton_3);
		
		radioButton_4 = new JRadioButton("5");
		radioButton_4.setFocusable(false);
		radioButton_4.setForeground(Color.WHITE);
		radioButton_4.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		radioButton_4.setBackground(Color.BLACK);
		radioButton_4.setBounds(329, 372, 35, 23);
		card.add(radioButton_4);
		
		ButtonGroup radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(radioButton);
		radioButtonGroup.add(radioButton_1);
		radioButtonGroup.add(radioButton_2);
		radioButtonGroup.add(radioButton_3);
		radioButtonGroup.add(radioButton_4);
		
		JLabel background = new JLabel("");
		background.setFocusable(false);
		background.setBackground(Color.BLACK);
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		
		txtrScriviLaTua.getDocument().addDocumentListener(new DocumentListener(){
		    public void insertUpdate(DocumentEvent event) {
		        wordCount.setText(txtrScriviLaTua.getDocument().getLength() + "/600");
		    }

		    public void removeUpdate(DocumentEvent e) {
		    	wordCount.setText(txtrScriviLaTua.getDocument().getLength() + "/600");
		    }

			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		
		
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int vote;
				if(radioButton.isSelected()){
					vote = 1;
				}else if(radioButton_1.isSelected()){
					vote = 2;
				}else if(radioButton_2.isSelected()){
					vote = 3;
				}else if(radioButton_3.isSelected()){
					vote = 4;
				}else{
					vote = 5;
				}
				try {
					MysqlDAOFactory.getInstance().getMysqlReviewManager().sendReview(new Review(MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername(), chosenGame.getGameTitle(), vote, txtrScriviLaTua.getText()));
					JOptionPane.showMessageDialog(card, "Recensione inviata correttamente! Verrà sottoposta ad analisi e poi eventualmente pubblicata. Riceverai un messaggio di conferma.");
					ScreenController.setPreviousScreen();
					((GameReviewView) ScreenController.getLoadedScreens().get("GAMEREVIEWSCREEN")).checkSendButtonVisibility();
				} catch (DatabaseConnectionException | QueryException e) {
					if(e instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile inviare la recensione: database offline.");
					}else if(e instanceof QueryException){
						JOptionPane.showMessageDialog(card, "Impossibile inviare la recensione: problemi con il database.");
					}
					
					e.printStackTrace();
				}
			}
		});
		
		return card;
	}
	
	/**
	 * Clears the ScreenView's JTextComponents so that when the User returns to
	 * the ScreenView they are empty again and ready to be filled.
	 */
	protected void clearTextFields() {
		txtrScriviLaTua.setText("Scrivi la tua recensione!");
		radioButton_2.setSelected(true);
		radioButton.setSelected(false);
		radioButton_1.setSelected(false);
		radioButton_3.setSelected(false);
		radioButton_4.setSelected(false);
	}
	/**
	 * Sets the Game for which the Review is going to be written.
	 * @param chosenGame the game the Review is being written for
	 */
	public void setGame(Game chosenGame){
		this.chosenGame = chosenGame;
		title.setText("Scrivi recensione per " + chosenGame.getGameTitle());
	}
}
