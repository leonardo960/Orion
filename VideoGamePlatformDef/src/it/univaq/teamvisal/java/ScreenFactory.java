package it.univaq.teamvisal.java;

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

public class ScreenFactory {
	private final static String WELCOMESCREEN = "WELCOMESCREEN";
	private final static String LOGINSCREEN = "LOGINSCREEN";
	private final static String USERREGISTRATIONSCREEN = "USERREGISTRATIONSCREEN";
	private final static String MODERATORREGISTRATIONSCREEN = "MODERATORREGISTRATIONSCREEN";
	private final static String USERHOMEPAGESCREEN = "USERHOMEPAGESCREEN";
	private final static String USERPROFILESCREEN = "USERPROFILESCREEN";
	private final static String MODERATORFUNCTIONSSCREEN = "MODERATORFUNCTIONSSCREEN";
	private final static String USERMANAGEMENTSCREEN = "USERMANAGEMENTSCREEN";
	private final static String MODERATORREQUESTSSCREEN = "MODERATORREQUESTSSCREEN";
	private final static String MODERATORDERANKSCREEN = "MODERATORDERANKSCREEN";
	private final static String GAMESELECTIONSCREEN = "GAMESELECTIONSCREEN";
	private final static String REVIEWMANAGEMENTSCREEN = "REVIEWMANAGEMENTSCREEN";
	private final static String GAMEPROFILESCREEN = "GAMEPROFILESCREEN";
	private final static String GAMEREVIEWSCREEN = "GAMEREVIEWSCREEN";
	
	public static ScreenView produceScreen(String screenType){
		switch(screenType){
		case WELCOMESCREEN:
			return new WelcomeScreenView();
		case LOGINSCREEN:
			return new LoginScreenView();
		case USERREGISTRATIONSCREEN:
			return new UserRegistrationView();
		case MODERATORREGISTRATIONSCREEN:
			return new ModeratorRegistrationView();
		case USERHOMEPAGESCREEN:
			return new UserHomepageView();
		case USERPROFILESCREEN:
			return new UserProfileView();
		case MODERATORFUNCTIONSSCREEN:
			return new ModeratorFunctionsView();
		case USERMANAGEMENTSCREEN:
			return new UserManagementView();
		case MODERATORREQUESTSSCREEN:
			return new ModeratorRequestsView();
		case MODERATORDERANKSCREEN:
			return new ModeratorDerankView();
		case GAMESELECTIONSCREEN:
			return new GameSelectionView();
		case REVIEWMANAGEMENTSCREEN:
			return new ReviewManagementView();
		case GAMEPROFILESCREEN:
			return new GameProfileView();
		case GAMEREVIEWSCREEN:
			return new GameReviewView();
		default:
			return null;
		}
	}
}
