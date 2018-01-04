package it.univaq.teamvisal.java.business.impl.utilities;

import it.univaq.teamvisal.java.presentation.GameProfileView;
import it.univaq.teamvisal.java.presentation.GameReviewView;
import it.univaq.teamvisal.java.presentation.GameSelectionView;
import it.univaq.teamvisal.java.presentation.LoginScreenView;
import it.univaq.teamvisal.java.presentation.ModeratorDerankView;
import it.univaq.teamvisal.java.presentation.ModeratorFunctionsView;
import it.univaq.teamvisal.java.presentation.ModeratorRegistrationView;
import it.univaq.teamvisal.java.presentation.ModeratorRequestsView;
import it.univaq.teamvisal.java.presentation.ReviewManagementView;
import it.univaq.teamvisal.java.presentation.UserHomepageView;
import it.univaq.teamvisal.java.presentation.UserManagementView;
import it.univaq.teamvisal.java.presentation.UserProfileView;
import it.univaq.teamvisal.java.presentation.UserRegistrationView;
import it.univaq.teamvisal.java.presentation.WelcomeScreenView;
import it.univaq.teamvisal.java.presentation.WriteReviewView;
import it.univaq.teamvisal.java.presentation.utilities.ScreenView;

/**
 * Class which more or less adopts the Factory design pattern and whose functionality
 * is to return an instance of a ScreenView given a certain screenType in the form
 * of a String.
 * @author Leonardo Formichetti
 *
 */
public class ScreenFactory {
	/**
	 * Main method of the class: it produces an instance of the ScreenView specified
	 * by the String screenType.
	 * @param screenType the screen's name to be produced
	 * @return a new instance of the ScreenView
	 */
	public static ScreenView produceScreen(String screenType){
		switch(screenType){
		case "WELCOMESCREEN":
			return new WelcomeScreenView();
		case "LOGINSCREEN":
			return new LoginScreenView();
		case "USERREGISTRATIONSCREEN":
			return new UserRegistrationView();
		case "MODERATORREGISTRATIONSCREEN":
			return new ModeratorRegistrationView();
		case "USERHOMEPAGESCREEN":
			return new UserHomepageView();
		case "USERPROFILESCREEN":
			return new UserProfileView();
		case "MODERATORFUNCTIONSSCREEN":
			return new ModeratorFunctionsView();
		case "USERMANAGEMENTSCREEN":
			return new UserManagementView();
		case "MODERATORREQUESTSSCREEN":
			return new ModeratorRequestsView();
		case "MODERATORDERANKSCREEN":
			return new ModeratorDerankView();
		case "GAMESELECTIONSCREEN":
			return new GameSelectionView();
		case "REVIEWMANAGEMENTSCREEN":
			return new ReviewManagementView();
		case "GAMEPROFILESCREEN":
			return new GameProfileView();
		case "GAMEREVIEWSCREEN":
			return new GameReviewView();
		case "WRITEREVIEWSCREEN":
			return new WriteReviewView();
		default:
			return null;
		}
	}
}
