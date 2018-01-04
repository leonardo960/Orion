package it.univaq.teamvisal.java.business.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import it.univaq.teamvisal.java.business.impl.exceptions.DatabaseConnectionException;
import it.univaq.teamvisal.java.business.model.Game;
/**
 * Controller class which handles all the logic concerning the startup of games.
 * @author Leonardo Formichetti
 *
 */
public class GameplayController {
	/**
	 * Method that starts the specified Game and asks the JDBCUserManager to manage the
	 * storage of experience gained by the player once the gameplay ends.
	 * @param displayedGame the game to be played
	 * @throws InterruptedException 
	 * @throws IOException
	 * @throws DatabaseConnectionException
	 * @throws SQLException
	 */
	public void playGame(Game displayedGame) throws InterruptedException, IOException, DatabaseConnectionException, SQLException{
		
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "\"C:\\Users\\Leonardo Formichetti\\Repos\\Orion\\VideoGamePlatformDef\\games\\" + displayedGame.getGameTitle() + ".jar\"");
		Process p;
	    p = pb.start();
		p.waitFor();
		
		//Now, calculate experience based on game results.
		//We first find the savefile created by OrionAPI
		String gamesDirPath = "C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\games";
		File gamesDir = new File(gamesDirPath);
		File savefile = null;
	      FilenameFilter filter = new FilenameFilter() {
	         public boolean accept (File dir, String name) { 
	            return name.contains("savefile.txt");
	         } 
	      }; 
	      String[] files = gamesDir.list(filter);
	      if (files == null) {
	         System.out.println("Either directory does not exist or is not a directory"); 
	      } else { 
	            String filename = files[0];
	            savefile = new File(gamesDirPath + "\\" + filename);
	      }
	      

		//File savefile = new File("C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\games\\savefile.txt");
		if(!savefile.exists() || savefile == null){
			return;
		}else{
			int exp;
			FileReader fr = new FileReader(savefile);
			BufferedReader br = new BufferedReader(fr);
			exp = Integer.parseInt(br.readLine());
			JDBCUserManager.updateUserExp(exp);
			br.close();
			fr.close();
			savefile.delete();
		}
		
		
	}
}
