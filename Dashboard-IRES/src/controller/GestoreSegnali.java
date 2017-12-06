package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

import model.Cluster;
import model.Robot;
import model.Segnale;

public class GestoreSegnali implements Runnable {
	private HashMap<char[], Cluster> clusters = new HashMap<char[], Cluster>();
	private HashMap<char[], Robot> robots = new HashMap<char[], Robot>();
	private LinkedList<Segnale> segnali = new LinkedList<Segnale>();
	
	private void analizzaSegnale(){
		if(segnali.isEmpty()){
			return;
		}else{
			Segnale segnale = segnali.getLast();
			Robot robot = null;
			//Riconosciamo il Robot
			if(!robots.containsKey(segnale.getRobotID())){
				//Prima creaiamo il cluster se non c'è
				if(!clusters.containsKey(segnale.getClusterID())){
					clusters.put(segnale.getClusterID(), new Cluster(segnale.getClusterID()));
				}
				robot = new Robot(segnale.getSensorNumber(), segnale.getRobotID(), segnale.getClusterID());
				robots.put(segnale.getRobotID(), robot);
			}else{
				robot = robots.get(segnale.getRobotID());
			}
			//Analizziamo il segnale
			if(segnale.getValue()){
				robot.decrementDownSensors();
				robot.setSensor(segnale.getSensorNumber(), true);
				Storage.chiudiFinestraTemporale(segnale);
			}else{
				robot.incrementDownSensors();
				robot.setSensor(segnale.getSensorNumber(), false);
				Storage.apriFinestraTemporale(segnale);
			}
		}
	}
	
	private void calcolaIR(){
		
	}
	
	@Override
	public void run() {
		/*long begin = System.currentTimeMillis();
		
		long end = begin + 30000;
		
		while(begin < end){*/
		while(!segnali.isEmpty()){
			analizzaSegnale();
		}
			/*begin = System.currentTimeMillis();
		}
		
		calcolaIR();*/
	}
	
	class Ricettore implements Runnable{
		//Codice con JAXB che riceve gli XML con i segnali e li mette in "segnali"
		//Inoltre registra Cluster e Robot e popola "clusters" di conseguenza
		private Socket socket;
		private ObjectInputStream inputStream;
		
		public void inizializza(){
		try{
		socket = new Socket("localhost", 60010);
		inputStream = new ObjectInputStream(socket.getInputStream());
		}catch(Exception e){
			return;
			}
		}

		@Override
		public void run() {
			inizializza();
			
			while(true){
				try{
					Segnale segnale = (Segnale) inputStream.readObject();
					segnali.add(segnale);
				}catch(IOException | ClassNotFoundException e){
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
