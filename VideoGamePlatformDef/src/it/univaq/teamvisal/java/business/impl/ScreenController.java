
package it.univaq.teamvisal.java.business.impl;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.impl.utilities.ScreenFactory;
import it.univaq.teamvisal.java.presentation.WelcomeScreenView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;

/**
 * Main "engine" of the system and most important Controller class in charge of managing
 * all the logic related to switching Screens.
 * @author Leonardo Formichetti
 *
 */
public class ScreenController {

	private static ScreenView currentScreen;
	private static JPanel screenManager;
	private static JFrame window;
	private static TreeMap<String, ScreenView> loadedScreens;
	private static CardLayout screenManagerLayout;
	
	private static LogoutController logoutController;

	/**
	 * Asks the Screen Controller to switch to Screen screen. If the passed screen
	 * had already been loaded in memory then it is simply showed; otherwise, it is
	 * first created through the ScreenFactory.
	 * @param screen the Screen the application is going to show
	 * 
	 */
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
	/**
	 * Asks the ScreenController to switch to the Screen that precedes the current
	 * Screen in the application's logical display order. Doesn't need any param
	 * since ScreenController keeps automatically track of the current Screen.
	 */
	public static void setPreviousScreen(){
		switch(currentScreen.getScreenName()){
		case "WELCOMESCREEN":
			break;
		case "LOGINSCREEN":
			setScreen("WELCOMESCREEN");
			break;
		case "USERREGISTRATIONSCREEN":
			setScreen("WELCOMESCREEN");
			break;
		case "MODERATORREGISTRATIONSCREEN":
			setScreen("USERHOMEPAGESCREEN");
			break;
		case "USERHOMEPAGESCREEN":
			setScreen("WELCOMESCREEN");
			break;
		case "USERPROFILESCREEN":
			setScreen("USERHOMEPAGESCREEN");
			break;
		case "MODERATORFUNCTIONSSCREEN":
			setScreen("USERHOMEPAGESCREEN");
			break;
		case "GAMESELECTIONSCREEN":
			setScreen("USERHOMEPAGESCREEN");
			break;
		case "USERMANAGEMENTSCREEN":
			setScreen("MODERATORFUNCTIONSSCREEN");
		    break;
		case "MODERATORREQUESTSSCREEN":
			setScreen("USERMANAGEMENTSCREEN");
			break;
		case "MODERATORDERANKSCREEN":
			setScreen("USERMANAGEMENTSCREEN");
			break;
		case "REVIEWMANAGEMENTSCREEN":
			setScreen("MODERATORFUNCTIONSSCREEN");
			break;
		case "GAMEPROFILESCREEN":
			setScreen("GAMESELECTIONSCREEN");
			break;
		case "GAMEREVIEWSCREEN":
			setScreen("GAMEPROFILESCREEN");
			break;
		case "WRITEREVIEWSCREEN":
			setScreen("GAMEREVIEWSCREEN");
			break;
		default:
			break;
		}
	}
	
	/**
	 * Returns the TreeMap with screens' names as keys and screens themselves 
	 * as values. Serves both the purposes of checking if a Screen has already
	 * been loaded in memory and the retrieval of a Screen given its String name.
	 * 
	 * @return a TreeMap<String, ScreenView> as described above
	 */
	public static TreeMap<String, ScreenView> getLoadedScreens(){
		return loadedScreens;
	}
	
	/**
	 * The very first method that gets called when the application is executed. Initiates
	 * the View part of the application and sets the Welcome Screen as the first screen
	 * displayed.
	 */
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
		screenManager.add(currentScreen.initialize(), "WELCOMESCREEN");
		loadedScreens.put("WELCOMESCREEN", currentScreen);
		screenManagerLayout = (CardLayout)(screenManager.getLayout());
		screenManagerLayout.show(screenManager, "WELCOMESCREEN");
		
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
	}
	
}
