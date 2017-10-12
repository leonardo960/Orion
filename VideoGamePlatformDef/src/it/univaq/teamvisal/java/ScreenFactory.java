package it.univaq.teamvisal.java;

import it.univaq.teamvisal.java.presentation.LoginScreenView;
import it.univaq.teamvisal.java.presentation.ModeratorFunctionsView;
import it.univaq.teamvisal.java.presentation.ModeratorRegistrationView;
import it.univaq.teamvisal.java.presentation.UserHomepageView;
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
		default:
			return null;
		}
	}
}
