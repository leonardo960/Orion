package it.univaq.teamvisal.java.presentation.utilities;

import javax.swing.JPanel;
/**
 * Helper class which joins the common elements shared by all ScreenViews: a name,
 * a JPanel that contains all the GUI elements within the ScreenView and the getter
 * method for the name.
 * @author Leonardo Formichetti
 *
 */
public class ScreenViewSuper {

	protected String screenName;
	protected JPanel card = new JPanel();
	/**
	 * Getter method for the ScreenView's name.
	 * @return the ScreenView's name
	 */
	public String getScreenName(){
		return screenName;
	}
	
}
