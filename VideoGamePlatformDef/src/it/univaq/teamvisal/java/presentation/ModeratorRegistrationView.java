package it.univaq.teamvisal.java.presentation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.DocumentSizeFilter;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.ModeratorRequestController;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.JTextArea;
import java.sql.SQLException;


public class ModeratorRegistrationView extends ScreenViewSuper implements ScreenView {
	private JTextArea txtrRaccontaciCosaTi;
	private ModeratorRequestController moderatorRequestController;
	
	public ModeratorRegistrationView(){
		screenName = "MODERATORREGISTRATIONSCREEN";
		moderatorRequestController = new ModeratorRequestController();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel initialize() {
		JButton confirm = new JButton("Invia richiesta");
		confirm.setForeground(Color.WHITE);
		confirm.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		confirm.setBackground(Color.BLACK);
		confirm.setFocusable(false);
		confirm.setBounds(179, 315, 159, 23);
		JButton back = new JButton("Indietro");
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setBackground(Color.BLACK);
		back.setFocusable(false);
		back.setBounds(179, 349, 158, 23);
		confirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		txtrRaccontaciCosaTi = new JTextArea();
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ScreenController.setPreviousScreen(screenName);
				((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).updateMessages();
			}
		});
		
		card.setLayout(null);
		card.add(confirm);
		card.add(back);
		
		JLabel modRequest = new JLabel("Procedura di richiesta moderatore");
		modRequest.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		modRequest.setForeground(Color.WHITE);
		modRequest.setBounds(112, 38, 278, 87);
		card.add(modRequest);
		
		
		
		JLabel wordCount = new JLabel("55/400");
		wordCount.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		wordCount.setForeground(Color.WHITE);
		wordCount.setBounds(396, 264, 65, 30);
		card.add(wordCount);
		
		txtrRaccontaciCosaTi.getDocument().addDocumentListener(new DocumentListener(){
		    public void insertUpdate(DocumentEvent event) {
		        wordCount.setText(txtrRaccontaciCosaTi.getDocument().getLength() + "/400");
		    }

		    public void removeUpdate(DocumentEvent e) {
		    	wordCount.setText(txtrRaccontaciCosaTi.getDocument().getLength() + "/400");
		    }

			public void changedUpdate(DocumentEvent e) {
				
			}
		});
	    AbstractDocument doc = (AbstractDocument) txtrRaccontaciCosaTi.getDocument();
	    doc.setDocumentFilter(new DocumentSizeFilter(400));
		
		
		txtrRaccontaciCosaTi.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		txtrRaccontaciCosaTi.setLineWrap(true);
		txtrRaccontaciCosaTi.setText("Raccontaci cosa ti ha portato a voler lavorare con noi!");
		txtrRaccontaciCosaTi.setBounds(112, 136, 274, 158);
		card.add(txtrRaccontaciCosaTi);
		
		
		
		JLabel background = new JLabel("");
		background.setBounds(0, 0, 500, 500);
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\bg.jpg"));
		background.setBackground(Color.BLACK);
		card.add(background);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					moderatorRequestController.sendRequest(txtrRaccontaciCosaTi.getText());
					JOptionPane.showMessageDialog(card, "Richiesta inviata con successo! Grazie del tuo interesse. Ti verrà inviato un messaggio dal sistema con l'esito della richiesta.");
					((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).setRequestButton(false);
					ScreenController.setPreviousScreen(screenName);
					((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).updateMessages();
				} catch (DatabaseConnectionException | SQLException e) {
					if(e instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Invio richiesta fallito: database offline.");
					}else if(e instanceof SQLException){
						JOptionPane.showMessageDialog(card, "Invio richiesta fallito: problemi con il database.");
					}
				}
			}
		});
		
		return card;
	}

	
	public void clearTextFields() {
		txtrRaccontaciCosaTi.setText("Raccontaci cosa ti ha portato a voler lavorare con noi!");
	}
}
