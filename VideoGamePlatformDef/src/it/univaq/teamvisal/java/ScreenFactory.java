package it.univaq.teamvisal.java;

import it.univaq.teamvisal.java.business.impl.ScreenController;
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

public class ScreenFactory {
	
	public static ScreenView produceScreen(String screenType){
		switch(screenType){
		case ScreenController.WELCOMESCREEN:
			return new WelcomeScreenView();
		case ScreenController.LOGINSCREEN:
			return new LoginScreenView();
		case ScreenController.USERREGISTRATIONSCREEN:
			return new UserRegistrationView();
		case ScreenController.MODERATORREGISTRATIONSCREEN:
			return new ModeratorRegistrationView();
		case ScreenController.USERHOMEPAGESCREEN:
			return new UserHomepageView();
		case ScreenController.USERPROFILESCREEN:
			return new UserProfileView();
		case ScreenController.MODERATORFUNCTIONSSCREEN:
			return new ModeratorFunctionsView();
		case ScreenController.USERMANAGEMENTSCREEN:
			return new UserManagementView();
		case ScreenController.MODERATORREQUESTSSCREEN:
			return new ModeratorRequestsView();
		case ScreenController.MODERATORDERANKSCREEN:
			return new ModeratorDerankView();
		case ScreenController.GAMESELECTIONSCREEN:
			return new GameSelectionView();
		case ScreenController.REVIEWMANAGEMENTSCREEN:
			return new ReviewManagementView();
		case ScreenController.GAMEPROFILESCREEN:
			return new GameProfileView();
		case ScreenController.GAMEREVIEWSCREEN:
			return new GameReviewView();
		case ScreenController.WRITEREVIEWSCREEN:
			return new WriteReviewView();
		default:
			return null;
		}
	}
}
