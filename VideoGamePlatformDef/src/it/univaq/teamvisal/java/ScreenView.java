package it.univaq.teamvisal.java;

import javax.swing.JPanel;
/**
 * Interface for the Screen Views. Infact, any Screen View has to be initialized and
 * will surely have a name, accessed via the relative getter method.
 * @author Leonardo Formichetti
 *
 */
public interface ScreenView {
	
/**
 * Initializes the ScreenView by creating the associated JPanel, populating
 * it with JComponents and then returning it in the end.
 */
public JPanel initialize();
/**
 * Getter method for the Screen's name.
 */
public String getScreenName();
}
