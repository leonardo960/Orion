package it.univaq.teamvisal.java.business.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import it.univaq.teamvisal.java.DatabaseConnectionException;
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
		
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "\"C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\games\\" + displayedGame.getGameTitle() + ".jar\"");
		Process p;
	    p = pb.start();
		p.waitFor();
		
		//Now, calculate experience based on game results.
		
		File savefile = new File("C:\\Users\\Leonardo Formichetti\\git\\VideoGamePlatformDef\\games\\savefile.txt");
		if(!savefile.exists()){
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
