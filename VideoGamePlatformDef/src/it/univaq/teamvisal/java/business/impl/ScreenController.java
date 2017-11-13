
package it.univaq.teamvisal.java.business.impl;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.univaq.teamvisal.java.DatabaseConnectionException;
import it.univaq.teamvisal.java.ScreenFactory;
import it.univaq.teamvisal.java.ScreenView;
import it.univaq.teamvisal.java.presentation.WelcomeScreenView;

public class ScreenController {

	private static ScreenView currentScreen;
	private static JPanel screenManager;
	private static JFrame window;
	private static TreeMap<String, ScreenView> loadedScreens;
	private static CardLayout screenManagerLayout;
	
	public final static String WELCOMESCREEN = "WELCOMESCREEN";
	public final static String LOGINSCREEN = "LOGINSCREEN";
	public final static String USERREGISTRATIONSCREEN = "USERREGISTRATIONSCREEN";
	public final static String MODERATORREGISTRATIONSCREEN = "MODERATORREGISTRATIONSCREEN";
	public final static String USERHOMEPAGESCREEN = "USERHOMEPAGESCREEN";
	public final static String USERPROFILESCREEN = "USERPROFILESCREEN";
	public final static String MODERATORFUNCTIONSSCREEN = "MODERATORFUNCTIONSSCREEN";
	public final static String GAMESELECTIONSCREEN = "GAMESELECTIONSCREEN";
	public final static String USERMANAGEMENTSCREEN = "USERMANAGEMENTSCREEN";
	public final static String MODERATORREQUESTSSCREEN = "MODERATORREQUESTSSCREEN";
	public final static String MODERATORDERANKSCREEN = "MODERATORDERANKSCREEN";
	public final static String REVIEWMANAGEMENTSCREEN = "REVIEWMANAGEMENTSCREEN";
	public final static String GAMEPROFILESCREEN = "GAMEPROFILESCREEN";
	public final static String GAMEREVIEWSCREEN = "GAMEREVIEWSCREEN";
	public final static String WRITEREVIEWSCREEN = "WRITEREVIEWSCREEN";
	
	private static LogoutController logoutController;

	
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
			screenManagerLayout.show(screenManager, USERHOMEPAGESCREEN);
			break;
		case USERMANAGEMENTSCREEN:
			screenManagerLayout.show(screenManager, MODERATORFUNCTIONSSCREEN);
		    break;
		case MODERATORREQUESTSSCREEN:
			screenManagerLayout.show(screenManager, USERMANAGEMENTSCREEN);
			break;
		case MODERATORDERANKSCREEN:
			screenManagerLayout.show(screenManager, USERMANAGEMENTSCREEN);
			break;
		case REVIEWMANAGEMENTSCREEN:
			screenManagerLayout.show(screenManager, MODERATORFUNCTIONSSCREEN);
			break;
		case GAMEPROFILESCREEN:
			screenManagerLayout.show(screenManager, GAMESELECTIONSCREEN);
			break;
		case GAMEREVIEWSCREEN:
			screenManagerLayout.show(screenManager, GAMEPROFILESCREEN);
			break;
		case WRITEREVIEWSCREEN:
			screenManagerLayout.show(screenManager, GAMEREVIEWSCREEN);
			break;
		default:
			break;
		}
	}
	
	public static TreeMap<String, ScreenView> getLoadedScreens(){
		return loadedScreens;
	}
	
	public static void begin(){
		logoutController = new LogoutController();
		loadedScreens = new TreeMap<String, ScreenView>();
		
		window = new JFrame("Orion");
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
	
}
