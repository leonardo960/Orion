package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.ScreenViewSuper;
import it.univaq.teamvisal.java.business.impl.JDBCUserManager;
import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.model.User;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserProfileView extends ScreenViewSuper implements ScreenView  {
	private JLabel trofeo2, trofeo3, trofeo4, trofeo5;
	private JLabel username, name, surname, exp, level;
	public UserProfileView(){
		screenName = "USERPROFILESCREEN";
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public JPanel initialize() {
		JPanel card = new JPanel();
		card.setLayout(null);
		
		username = new JLabel("Username: " + JDBCUserManager.getCurrentUser().getUsername());
		username.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		username.setForeground(Color.WHITE);
		username.setBounds(78, 92, 246, 14);
		card.add(username);
		
		name = new JLabel("Nome: " + JDBCUserManager.getCurrentUser().getNome());
		name.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		name.setForeground(Color.WHITE);
		name.setBounds(78, 117, 246, 14);
		card.add(name);
		
		surname = new JLabel("Cognome: " + JDBCUserManager.getCurrentUser().getCognome());
		surname.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		surname.setForeground(Color.WHITE);
		surname.setBounds(78, 142, 246, 14);
		card.add(surname);
		
		exp = new JLabel("Exp: " + JDBCUserManager.getCurrentUser().getExp());
		exp.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		exp.setForeground(Color.WHITE);
		exp.setBounds(78, 167, 246, 14);
		card.add(exp);
		
		level = new JLabel("Livello: " + JDBCUserManager.getCurrentUser().getLevel());
		level.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		level.setForeground(Color.WHITE);
		level.setBounds(78, 192, 246, 14);
		card.add(level);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setPreviousScreen(screenName);
			}
		});
		back.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(10, 419, 124, 36);
		card.add(back);
		
		JLabel trofeo1 = new JLabel("Trofeo di Benvenuto - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(0));
		trofeo1.setBackground(Color.BLACK);
		trofeo1.setForeground(Color.WHITE);
		trofeo1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		trofeo1.setBounds(78, 294, 350, 14);
		card.add(trofeo1);
		
		trofeo2 = new JLabel("Trofeo Lira ");
		trofeo2.setBackground(Color.BLACK);
		trofeo2.setForeground(Color.WHITE);
		trofeo2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		trofeo2.setBounds(78, 319, 350, 14);
		card.add(trofeo2);
		
		trofeo3 = new JLabel("Trofeo Bilancia ");
		trofeo3.setBackground(Color.BLACK);
		trofeo3.setForeground(Color.WHITE);
		trofeo3.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		trofeo3.setBounds(78, 344, 350, 14);
		card.add(trofeo3);
		
		trofeo4 = new JLabel("Trofeo Paradiso ");
		trofeo4.setBackground(Color.BLACK);
		trofeo4.setForeground(Color.WHITE);
		trofeo4.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		trofeo4.setBounds(78, 369, 350, 14);
		card.add(trofeo4);
		
		trofeo5 = new JLabel("Trofeo Orion ");
		trofeo5.setBackground(Color.BLACK);
		trofeo5.setForeground(Color.WHITE);
		trofeo5.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		trofeo5.setBounds(78, 394, 350, 14);
		card.add(trofeo5);
		
		JLabel trofei = new JLabel("Timeline Trofei");
		trofei.setHorizontalAlignment(SwingConstants.CENTER);
		trofei.setForeground(new Color(255, 255, 102));
		trofei.setBackground(Color.BLACK);
		trofei.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		trofei.setBounds(144, 230, 180, 36);
		card.add(trofei);
		
		JLabel profileTitle = new JLabel("Profilo Utente");
		profileTitle.setBackground(Color.BLACK);
		profileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		profileTitle.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		profileTitle.setForeground(new Color(0, 153, 204));
		profileTitle.setBounds(144, 45, 180, 36);
		card.add(profileTitle);
		
		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\workspace\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		
		setTrophyDates();
		return card;
	}
	
	public void setTrophyDates(){
		switch(JDBCUserManager.getCurrentUser().getLvlDates().size()){
		case 1:
			trofeo2.setText("Trofeo Lira - Non acquisito");
			trofeo3.setText("Trofeo Bilancia - Non acquisito");
			trofeo4.setText("Trofeo Paradiso - Non acquisito");
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 2:
			trofeo2.setText("Trofeo Lira - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(1));
			trofeo3.setText("Trofeo Bilancia - Non acquisito");
			trofeo4.setText("Trofeo Paradiso - Non acquisito");
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 3:
			trofeo2.setText("Trofeo Lira - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(1));
			trofeo3.setText("Trofeo Bilancia - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(2));
			trofeo4.setText("Trofeo Paradiso - Non acquisito");
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 4:
			trofeo2.setText("Trofeo Lira - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(1));
			trofeo3.setText("Trofeo Bilancia - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(2));
			trofeo4.setText("Trofeo Paradiso - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(3));
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 5:
			trofeo2.setText("Trofeo Lira - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(1));
			trofeo3.setText("Trofeo Bilancia - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(2));
			trofeo4.setText("Trofeo Paradiso - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(3));
			trofeo5.setText("Trofeo Orion - Acquisito il " + JDBCUserManager.getCurrentUser().getLvlDates().get(4));
			break;
		default:
			break;
		}
	}
	
	public void setProfileInformation(){
		username.setText("Username: " + JDBCUserManager.getCurrentUser().getUsername());
		name.setText("Nome: " + JDBCUserManager.getCurrentUser().getNome());
		surname.setText("Cognome: " + JDBCUserManager.getCurrentUser().getCognome());
		exp.setText("Exp: " + JDBCUserManager.getCurrentUser().getExp());
		level.setText("Livello: " + JDBCUserManager.getCurrentUser().getLevel());
	}
	
	@Override
	protected void clearTextFields() {
		//THIS FUNCTION WILL NEVER BE CALLED HERE
	}
}
