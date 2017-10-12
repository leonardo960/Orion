package it.univaq.teamvisal.java;

public abstract class ScreenViewSuper {

	protected String screenName;
	
	public String getScreenName(){
		return screenName;
	}
	
	abstract protected void clearTextFields();
}
