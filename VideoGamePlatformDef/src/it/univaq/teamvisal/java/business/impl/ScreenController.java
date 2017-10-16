package it.univaq.teamvisal.java.business.impl;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenFactory;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.presentation.LoginScreenView;
import it.univaq.teamvisal.java.presentation.ModeratorRegistrationView;
import it.univaq.teamvisal.java.presentation.UserRegistrationView;
import it.univaq.teamvisal.java.presentation.WelcomeScreenView;

public class ScreenController {

	private static ScreenView currentScreen;
	private static JPanel screenManager;
	private JFrame window;
	private static TreeMap<String, ScreenView> loadedScreens;
	private static CardLayout screenManagerLayout;
	
	private final static String WELCOMESCREEN = "WELCOMESCREEN";
	private final static String LOGINSCREEN = "LOGINSCREEN";
	private final static String USERREGISTRATIONSCREEN = "USERREGISTRATIONSCREEN";
	private final static String MODERATORREGISTRATIONSCREEN = "MODERATORREGISTRATIONSCREEN";
	private final static String USERHOMEPAGESCREEN = "USERHOMEPAGESCREEN";
	private final static String USERPROFILESCREEN = "USERPROFILESCREEN";
	private final static String MODERATORFUNCTIONSSCREEN = "MODERATORFUNCTIONSSCREEN";
	private final static String GAMESELECTIONSCREEN = "GAMESELECTIONSCREEN";
	
	private LogoutController logoutController;
	
	
	public ScreenController(){
		logoutController = new LogoutController();
		loadedScreens = new TreeMap<String, ScreenView>();
		
		window = new JFrame("Orion");
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE
				);
		window.setSize(500, 500);
		window.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			if(JDBCUserManager.isUserLogged()){
			    if(JOptionPane.showConfirmDialog(window, "Sei sicuro di voler uscire? (questo effettuerà il logout)", "Conferma uscita", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				try {
					logoutController.logout();
					System.exit(0);
				} catch (DatabaseConnectionException | SQLException e1) {
					if(e1 instanceof DatabaseConnectionException){
						if(JOptionPane.showConfirmDialog(window, "Procedura di logout fallita: database offline.\nSi desidera uscire comunque? (I progressi nei vari giochi non saranno salvati)") == JOptionPane.YES_OPTION){
							System.exit(0);
						}
					}else if(e1 instanceof SQLException){
						if(JOptionPane.showConfirmDialog(window, "Procedura di logout fallita a causa di problemi con il database.\nSi desidera uscire comunque? (I progressi nei vari giochi non saranno salvati)") == JOptionPane.YES_OPTION){
							System.exit(0);
						}
					}
				}
				
			    }else{
			    	//DO NOTHING
			    } 
			  }else{
				  System.exit(0);
			  }
			 }
			});
		screenManager = new JPanel();
		screenManager.setLayout(new CardLayout());
		
		window.add(screenManager);
		
		currentScreen = new WelcomeScreenView();
		screenManager.add(currentScreen.initialize(), WELCOMESCREEN);
		loadedScreens.put(WELCOMESCREEN, currentScreen);
		screenManagerLayout = (CardLayout)(screenManager.getLayout());
		screenManagerLayout.show(screenManager, WELCOMESCREEN);
		
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
	}
	
	public static void setScreen(String screen){
		if(!loadedScreens.containsValue(currentScreen)){
			loadedScreens.put(currentScreen.getScreenName(), currentScreen);
			}
		if(loadedScreens.containsKey(screen)){
			currentScreen = loadedScreens.get(screen);
			screenManagerLayout.show(screenManager, screen);
		}else{
			currentScreen = ScreenFactory.produceScreen(screen);
			loadedScreens.put(screen, currentScreen);
			screenManager.add(currentScreen.initialize(), screen);
			screenManagerLayout.show(screenManager, screen);
		}
	}
	
	public static void setPreviousScreen(String currentScreen){
		switch(currentScreen){
		case WELCOMESCREEN:
			break;
		case LOGINSCREEN:
			screenManagerLayout.show(screenManager, WELCOMESCREEN);
			break;
		case USERREGISTRATIONSCREEN:
			screenManagerLayout.show(screenManager, WELCOMESCREEN);
			break;
		case MODERATORREGISTRATIONSCREEN:
			screenManagerLayout.show(screenManager, USERHOMEPAGESCREEN);
			break;
		case USERHOMEPAGESCREEN:
			screenManagerLayout.show(screenManager, WELCOMESCREEN);
			break;
		case USERPROFILESCREEN:
			screenManagerLayout.show(screenManager, USERHOMEPAGESCREEN);
			break;
		case MODERATORFUNCTIONSSCREEN:
			screenManagerLayout.show(screenManager, USERHOMEPAGESCREEN);
			break;
		case GAMESELECTIONSCREEN:
			screenManagerLayout.show(screenManager, GAMESELECTIONSCREEN);
		default:
			break;
		}
	}
	
	public static TreeMap<String, ScreenView> getLoadedScreens(){
		return loadedScreens;
	}
	
	
}
