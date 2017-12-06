package model;

import java.sql.Timestamp;
import java.util.Random;

public class Segnale {
	private char[] robotID;
	private char[] clusterID;
	private byte sensorNumber;
	private boolean value;
	private Timestamp timestamp;
	
	public Segnale(char[] robotID, char[] clusterID, byte sensorNumber, boolean value, long timestamp){
		this.robotID = robotID;
		this.clusterID = clusterID;
		this.sensorNumber = sensorNumber;
		this.value = value;
		this.timestamp = new Timestamp(timestamp);
	}
	/**
	 * Generates a random Signal for testing purposes
	 */
	public Segnale(){
		//Random rand = new Random();
		
		//Va inserita la logica con cui si generano segnali random
		//che tiene conto del fatto che magari qualche sensore è down
		//e quindi invio il segnale di up etc.
		//Comunque completamente random non va bene
 	}
	
	public boolean getValue(){
		return value;
	}
	
	public Timestamp getTimestamp(){
		return timestamp;
	}
	
	public char[] getRobotID(){
		return robotID;
	}
	
	public byte getSensorNumber(){
		return sensorNumber;
	}
	
	public char[] getClusterID(){
		return clusterID;
	}
}
