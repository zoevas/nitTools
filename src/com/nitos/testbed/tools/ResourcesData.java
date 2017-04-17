package com.nitos.testbed.tools;

import java.util.ArrayList;

public class ResourcesData {

	ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	String uuid;
	
	public ResourcesData(){
		
	}
	
	public ResourcesData(String uuid){
	   this.uuid = uuid;
	}
	
	public String getUUID(){
		return this.uuid;
	}
	
	public void setUUID(String uuid){
		this.uuid = uuid;
	}
	
	public ArrayList<Reservation> getReservations(){
		return this.reservations;
	}
	
	public void setReservations(Reservation reservation){
		this.reservations.add(reservation);
	}
	
}
