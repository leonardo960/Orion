package controller;

import model.Segnale;

public class IRES {

	public static void main(String[] args) {
		GestoreSegnali gs = new GestoreSegnali();
		
		long begin = System.currentTimeMillis();
		
		new Thread(gs).start();
		
		long end = System.currentTimeMillis();
		
		long timeOfExecution = (end - begin)/1000l;
		
		System.out.println("Tempo di esecuzione: " + (timeOfExecution));
	}

}
