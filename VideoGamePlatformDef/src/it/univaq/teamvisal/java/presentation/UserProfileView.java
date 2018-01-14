package it.univaq.teamvisal.java.presentation;

import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.ScreenController;
import it.univaq.teamvisal.java.business.impl.dao.MysqlDAOFactory;
import it.univaq.teamvisal.java.business.model.Trophy;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenViewSuper;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * ScreenView for the User to check his/her profile. All account information are displayed
 * plus all the achievements the user has gained playing the games.
 * @author Leonardo Formichetti
 *
 */
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
		card.setLayout(null);
		
		username = new JLabel("Username: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername());
		username.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		username.setForeground(Color.WHITE);
		username.setBounds(78, 92, 246, 14);
		card.add(username);
		
		name = new JLabel("Nome: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getNome());
		name.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		name.setForeground(Color.WHITE);
		name.setBounds(78, 117, 246, 14);
		card.add(name);
		
		surname = new JLabel("Cognome: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getCognome());
		surname.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		surname.setForeground(Color.WHITE);
		surname.setBounds(78, 142, 246, 14);
		card.add(surname);
		
		exp = new JLabel("Exp: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getExp());
		exp.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		exp.setForeground(Color.WHITE);
		exp.setBounds(78, 167, 246, 14);
		card.add(exp);
		
		level = new JLabel("Livello: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getLevel());
		level.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 15));
		level.setForeground(Color.WHITE);
		level.setBounds(78, 192, 246, 18);
		card.add(level);
		
		JButton back = new JButton("Indietro");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScreenController.setPreviousScreen();
				((UserHomepageView) ScreenController.getLoadedScreens().get("USERHOMEPAGESCREEN")).updateMessages();
			}
		});
		back.setFocusable(false);
		back.setBackground(Color.BLACK);
		back.setForeground(Color.WHITE);
		back.setBounds(10, 419, 124, 36);
		card.add(back);
		
		JLabel trofeo1 = new JLabel("Trofeo di Benvenuto - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo di Benvenuto")));
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
		background.setIcon(new ImageIcon("C:\\Users\\Leonardo Formichetti\\repos\\Orion\\VideoGamePlatformDef\\bg.jpg"));
		background.setBounds(0, 0, 500, 500);
		card.add(background);
		
		
		setTrophyDates();
		return card;
	}
	
	/**
	 * Sets the dates when the user gained the various trophies available in the platform,
	 * retrieving them thanks to the User DAO
	 */
	public void setTrophyDates(){
		switch(MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().size()){
		case 1:
			trofeo2.setText("Trofeo Lira - Non acquisito");
			trofeo3.setText("Trofeo Bilancia - Non acquisito");
			trofeo4.setText("Trofeo Paradiso - Non acquisito");
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 2:
			trofeo2.setText("Trofeo Lira - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Lira")));
			trofeo3.setText("Trofeo Bilancia - Non acquisito");
			trofeo4.setText("Trofeo Paradiso - Non acquisito");
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 3:
			trofeo2.setText("Trofeo Lira - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Lira")));
			trofeo3.setText("Trofeo Bilancia - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Bilancia")));
			trofeo4.setText("Trofeo Paradiso - Non acquisito");
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 4:
			trofeo2.setText("Trofeo Lira - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Lira")));
			trofeo3.setText("Trofeo Bilancia - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Bilancia")));
			trofeo4.setText("Trofeo Paradiso - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Paradiso")));
			trofeo5.setText("Trofeo Orion - Non acquisito");
			break;
		case 5:
			trofeo2.setText("Trofeo Lira - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Lira")));
			trofeo3.setText("Trofeo Bilancia - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Bilancia")));
			trofeo4.setText("Trofeo Paradiso - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Paradiso")));
			trofeo5.setText("Trofeo Orion - Acquisito il " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getTrophies().get(new Trophy("Trofeo Orion")));
			break;
		default:
			break;
		}
	}
	
	/**
	 * Sets all the GUI components with the right data of the currentUser coming from the
	 * User DAO
	 */
	public void setProfileInformation(){
		username.setText("Username: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getUsername());
		name.setText("Nome: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getNome());
		surname.setText("Cognome: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getCognome());
		exp.setText("Exp: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getExp());
		level.setText("Livello: " + MysqlDAOFactory.getInstance().getMysqlUserManager().getCurrentUser().getLevel());
	}
	

}
