package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.exceptions.QueryException;
import it.univaq.teamvisal.java.business.model.Game;
import it.univaq.teamvisal.java.business.model.Review;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.TreeMap;
import java.awt.event.ActionEvent;

/**
 * ScreenView for viewing all the approved Reviews associated with a particular Game,
 * displayed in a List fashion.
 * @author Leonardo Formichetti
 *
 */
public class GameReviewView extends ScreenViewSuper implements ScreenView {
	private JLabel title;
	private JList<String> list;
	private List<Review> reviews;
	private TreeMap<Integer, Review> listRowToReview;
	private DefaultListModel<String> model;
	private Game chosenGame;
	private JButton leaveReview;
	public GameReviewView(){
		screenName = "GAMEREVIEWSCREEN";
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		JPanel card = new JPanel();
		card.setLayout(null);
		
		title = new JLabel("Recensioni di: ");
		title.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setForeground(Color.WHITE);
		title.setBounds(30, 42, 338, 74);
		card.add(title);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setPreviousScreen();
			}
		});
		back.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		back.setBounds(30, 410, 106, 48);
		card.add(back);
		
		list = new JList<String>();
		list.setToolTipText("");
		list.setBackground(Color.BLACK);
		list.setForeground(Color.WHITE);
		list.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 10));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setOpaque(false);
		list.setBounds(40, 124, 450, 258);
		card.add(list);
		
		leaveReview = new JButton("Scrivi recensione");
		leaveReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setScreen("WRITEREVIEWSCREEN");
				((WriteReviewView) ScreenController.getLoadedScreens().get("WRITEREVIEWSCREEN")).clearTextFields();
				((WriteReviewView) ScreenController.getLoadedScreens().get("WRITEREVIEWSCREEN")).setGame(chosenGame);
			}
		});
		leaveReview.setForeground(Color.WHITE);
		leaveReview.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		leaveReview.setFocusable(false);
		leaveReview.setBackground(Color.BLACK);
		leaveReview.setBounds(248, 410, 181, 48);
		card.add(leaveReview);
		
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
		        	JTextArea msg = new JTextArea(listRowToReview.get(list.getSelectedIndex()).getText());
		        	UIManager.put("OptionPane.minimumSize", new Dimension(500,300));
		        	msg.setLineWrap(true);
		        	msg.setWrapStyleWord(true);
		        	JScrollPane scrollPane = new JScrollPane(msg);
		        	JOptionPane.showMessageDialog(card, scrollPane, "Recensione di: " + listRowToReview.get(list.getSelectedIndex()).getUsername() + "    Voto:" + listRowToReview.get(list.getSelectedIndex()).getVote() + "/5", JOptionPane.INFORMATION_MESSAGE);
		        	UIManager.put("OptionPane.minimumSize", new Dimension(550,50));
		        }
		    }
		});
		
		return card;
	}

	
	public void checkSendButtonVisibility(){
				try {
					leaveReview.setVisible(!(MysqlDAOFactory.getInstance().getMysqlReviewManager().hasUserSentReviewFor(chosenGame)));
				} catch (QueryException | DatabaseConnectionException e) {
					if(e instanceof DatabaseConnectionException){
						JOptionPane.showMessageDialog(card, "Impossibile controllare se l'utente ha mandato o no la recensione: database offline.");
					}else if(e instanceof QueryException){
						JOptionPane.showMessageDialog(card, "Impossibile controllare se l'utente ha mandato o no la recensione: problemi con il database.");
					}
					e.printStackTrace();
				}	
	}
	public void populateList(Game game){
		try {
			chosenGame = game;
			title.setText("Recensioni di: " + game.getGameTitle());
			reviews = MysqlDAOFactory.getInstance().getMysqlReviewManager().getReviewsForGame(game);
			listRowToReview = new TreeMap<Integer, Review>();
			model = new DefaultListModel<String>();
			int i = 0;
			for(Review r : reviews){
				String row;
				row = r.getUsername() + " - " + r.getGamename() + "    Voto: " + r.getVote() + "/5";
				model.addElement(row);
				listRowToReview.put(i++, r);
			}
			list.setModel(model);
		} catch (DatabaseConnectionException | QueryException e) {
			if(e instanceof DatabaseConnectionException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le recensioni: database offline.");
				ScreenController.setPreviousScreen();
			}else if(e instanceof QueryException){
				JOptionPane.showMessageDialog(card, "Impossibile caricare le recensioni: problemi con il database.");
				ScreenController.setPreviousScreen();
			}
		}
	}
}
