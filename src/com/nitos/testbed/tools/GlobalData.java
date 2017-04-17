package com.nitos.testbed.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import android.app.Application;
import android.content.SharedPreferences;
import android.widget.CheckBox;


public class GlobalData extends Application{

	//Each HashMap represents one kind of nodes 
	public HashMap<String, ResourcesData> orbitNodes = new HashMap<String,  ResourcesData>();
	public HashMap<String, ResourcesData> gridNodes = new HashMap<String,  ResourcesData>();
	public HashMap<String, ResourcesData> usrpNodes = new HashMap<String,  ResourcesData>();
	public HashMap<String, ResourcesData> disklessNodes = new HashMap<String,  ResourcesData>();
	public HashMap<String, ResourcesData> icarusNodes = new HashMap<String,  ResourcesData>();
	public HashMap<String, ResourcesData> baseStations = new HashMap<String,  ResourcesData>();
	public HashMap<String, ResourcesData> channels_802_11a = new HashMap<String, ResourcesData>();
	public HashMap<String, ResourcesData> channels_802_11bg = new HashMap<String,  ResourcesData>();
	
	
	//The ArrayLists with the current available nodes
	public TreeMap<String, String> orbitAvailableNodes = new TreeMap<String, String>();
	public TreeMap<String, String> gridAvailableNodes = new TreeMap<String, String>();
	public TreeMap<String, String> usrpAvailableNodes = new TreeMap<String, String>();
	public TreeMap<String, String> disklessAvailableNodes = new TreeMap<String, String>();
	public TreeMap<String, String> icarusAvailableNodes = new TreeMap<String, String>();
	public TreeMap<String, String> baseStationAvailable = new TreeMap<String, String>();
	public TreeMap<String, String> channels_802_11a_available = new TreeMap<String, String>();
	public TreeMap<String, String> channels_802_11bg_available = new TreeMap<String, String>();
	
	
	//The ArrayLists with the checkBoxes
	public ArrayList<CheckBox> orbitCheckBoxes = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> gridCheckBoxes = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> usrpCheckBoxes = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> disklessCheckBoxes = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> icarusCheckBoxes = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> baseStationCheckBoxes = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> channels_802_11a_checkBoxes = new ArrayList<CheckBox>();
	public ArrayList<CheckBox> channels_802_11bg_checkBoxes = new ArrayList<CheckBox>();
	
	//The date and time given by the user
	public LocalTime timeUserFrom;
	public LocalDate dateUserFrom;
	
	public float duration;
	
	DateTime dateTimeUserFrom;
	DateTime dateTimeUserUntil;
	
	public SharedPreferences nodesSharedPreferences;
	
	
	 //Accessors for nodes
	 public HashMap<String,  ResourcesData> getOrbitNodes() {
		    return this.orbitNodes;
     }

	 public HashMap<String,  ResourcesData> getGridNodes() {
		    return this.gridNodes;
	 }
	 
	 public HashMap<String,  ResourcesData> getUsrpNodes() {
		    return this.usrpNodes;
     }
	 
	 public HashMap<String,  ResourcesData> getDisklessNodes() {
		    return this.disklessNodes;
     }
	 
	 public HashMap<String,  ResourcesData> getIcarusNodes() {
		    return this.icarusNodes;
     }
	 
	 public HashMap<String,  ResourcesData> getBaseStations() {
		    return this.baseStations;
     }
	 
	 public HashMap<String,  ResourcesData> getChannels802_11a() {
		    return this.channels_802_11a;
     }
	  
	 public HashMap<String,  ResourcesData> getChannels802_11bg() {
		    return this.channels_802_11bg;
     }
	 
	 

	 
	 //Accessor and methods for orbit available nodes
	 public  TreeMap<String, String> getOrbitAvailableNodes() {
		    return this.orbitAvailableNodes;
     }
	 
	 public  void setOrbitAvailableNodes(String node_name, String uuid) {
		   this.orbitAvailableNodes.put(node_name, uuid);
     }
	 
	 public void clearOrbitAvailableNodes(){
		 this.orbitAvailableNodes.clear();
	 }
	 
	 //Accessor and methods for grid available nodes
	 public   TreeMap<String, String> getGridAvailableNodes() {
		    return this.gridAvailableNodes;
     }
	 
	 public  void setGridAvailableNodes(String node_name, String uuid) {
		   this.gridAvailableNodes.put(node_name, uuid);
     }
	 
	 public void clearGridAvailableNodes(){
		 this.gridAvailableNodes.clear();
	 }
	 
	 //Accessor and methods for USRP available nodes
	 public   TreeMap<String, String> getUsrpAvailableNodes() {
		    return this.usrpAvailableNodes;
     }
	 
	 public  void setUsrpAvailableNodes(String node_name, String uuid) {
		   this.usrpAvailableNodes.put(node_name, uuid);
     }
	 
	 public void clearUsrpAvailableNodes(){
		 this.usrpAvailableNodes.clear();
	 }
	 
	 //Accessor and methods for Diskless available nodes
	 public  TreeMap<String, String> getDisklessAvailableNodes() {
		    return this.disklessAvailableNodes;
     }
	 
	 public  void setDisklessAvailableNodes(String node_name, String uuid) {
		   this.disklessAvailableNodes.put(node_name, uuid);
     }
	 	 
	 public void clearDisklessAvailableNodes(){
		 this.disklessAvailableNodes.clear();
	 }
	 
	 //Accessor and methods for Icarus available nodes
	 public  TreeMap<String, String> getIcarusAvailableNodes() {
		    return this.icarusAvailableNodes;
     }
	 
	 public  void setIcarusAvailableNodes(String node_name, String uuid) {
		   this.icarusAvailableNodes.put(node_name, uuid);
     }
	 
	 public void clearIcarusAvailableNodes(){
		 this.icarusAvailableNodes.clear();
	 }
	 
	 //Accessor and methods for Base Stations available nodes
	 public  TreeMap<String, String> getBaseStationsAvailable() {
		    return this.baseStationAvailable;
     }
	 
	 public  void setBaseStationsAvailable(String node_name, String uuid) {
		   this.baseStationAvailable.put(node_name, uuid);
     }
	 
	 public void clearBaseStationsAvailable(){
		 this.baseStationAvailable.clear();
	 }
	 
	 //Accessor and methods for channels 802.11.a available
	 public  TreeMap<String, String> getChannels_802_11a_available() {
		    return this.channels_802_11a_available;
     }
	 
	 public  void setChannels_802_11a_available(String channel_name, String uuid) {
		   this.channels_802_11a_available.put(channel_name, uuid);
     }
	 
	 public void clearChannels_802_11a_available(){
		 this.channels_802_11a_available.clear();
	 }
	 
	 //Accessor and methods for channels 802.11.bg available 
	 public  TreeMap<String, String> getChannels_802_11bg_available() {
		    return this.channels_802_11bg_available;
     }
	 
	 public  void setChannels_802_11bg_available(String channel_name, String uuid) {
		   this.channels_802_11bg_available.put(channel_name, uuid);
     }
	 
	 public void clearChannels_802_11bg_available(){
		 this.channels_802_11bg_available.clear();
	 }
	 
	 
	 
	 //Accessor and methods for orbit checkboxes
	 public  ArrayList<CheckBox> getOrbitCheckBoxes() {
		    return this.orbitCheckBoxes;
     }
	 
	 public  void setOrbitCheckBoxes(CheckBox checkbox) {
		   this.orbitCheckBoxes.add(checkbox);
     }
	 
	 public void setOrbitCheckBoxesText(int i, String text){
		 this.orbitCheckBoxes.get(i).setText(text);
	 }
	 
	 public void clearOrbitCheckBoxes(){
		 this.orbitCheckBoxes.clear();
	 }
	 
	//Accessor and methods for grid checkboxes
	 public  ArrayList<CheckBox> getGridCheckBoxes() {
		    return this.gridCheckBoxes;
	 }
	 
	 public  void setGridCheckBoxes(CheckBox checkbox) {
		    this.gridCheckBoxes.add(checkbox);
	 }
		 
	 public void setGridCheckBoxesText(int i, String text){
  	        this.gridCheckBoxes.get(i).setText(text);
  	 }
		 
	 public void clearGridCheckBoxes(){
	    	this.gridCheckBoxes.clear();
	 }
	 
	//Accessor and methods for usrp checkboxes
	 public  ArrayList<CheckBox> getUsrpCheckBoxes() {
		    return this.usrpCheckBoxes;
	 }
		 
	 public  void setUsrpCheckBoxes(CheckBox checkbox) {
		    this.usrpCheckBoxes.add(checkbox);
	 }
			 
	 public void setUsrpCheckBoxesText(int i, String text){
	        this.usrpCheckBoxes.get(i).setText(text);
	  }
			 
	 public void clearUsrpCheckBoxes(){
	    	this.usrpCheckBoxes.clear();
	 }
	 
	//Accessor and methods for Diskless checkboxes
	 public  ArrayList<CheckBox> getDisklessCheckBoxes() {
		    return this.disklessCheckBoxes;
	 }
			 
	 public  void setDisklessCheckBoxes(CheckBox checkbox) {
		    this.disklessCheckBoxes.add(checkbox);
	 }
				 
	 public void setDisklessCheckBoxesText(int i, String text){
	        this.disklessCheckBoxes.get(i).setText(text);
	  }
				 
	 public void clearDisklessCheckBoxes(){
	    	this.disklessCheckBoxes.clear();
	 }
	 
	//Accessor and methods for Icarus checkboxes
	 public  ArrayList<CheckBox> getIcarusCheckBoxes() {
		    return this.icarusCheckBoxes;
	 }
			 
	 public  void setIcarusCheckBoxes(CheckBox checkbox) {
		    this.icarusCheckBoxes.add(checkbox);
	 }
				 
	 public void setIcarusCheckBoxesText(int i, String text){
	        this.icarusCheckBoxes.get(i).setText(text);
	  }
				 
	 public void clearIcarusCheckBoxes(){
	    	this.icarusCheckBoxes.clear();
	 }
	 
	//Accessor and methods for Base station checkboxes
	 public  ArrayList<CheckBox> getBaseStationCheckBoxes() {
		    return this.baseStationCheckBoxes;
	 }
				 
	 public  void setBaseStationCheckBoxes(CheckBox checkbox) {
		    this.baseStationCheckBoxes.add(checkbox);
	 }
					 
	 public void setBaseStationCheckBoxesText(int i, String text){
		       this.baseStationCheckBoxes.get(i).setText(text);
	 }
					 
	 public void clearBaseStationCheckBoxes(){
	    	this.baseStationCheckBoxes.clear();
	 }
	 
	//Accessor and methods for channels 802.11.a checkboxes
	 public  ArrayList<CheckBox> getChannels_802_11a_checkBoxes() {
		    return this.channels_802_11a_checkBoxes;
	 }
			 
	 public  void setChannels_802_11a_checkBoxes(CheckBox checkbox) {
		    this.channels_802_11a_checkBoxes.add(checkbox);
	 }
				 
	 public void setChannels_802_11a_checkBoxesText(int i, String text){
	        this.channels_802_11a_checkBoxes.get(i).setText(text);
	  }
				 
	 public void clearChannels_802_11a_checkBoxes(){
	    	this.channels_802_11a_checkBoxes.clear();
	 }
	 
	 
	//Accessor and methods for channels 802.11.bg checkboxes
	 public  ArrayList<CheckBox> getChannels_802_11bg_checkBoxes() {
		    return this.channels_802_11bg_checkBoxes;
	 }
			 
	 public  void setChannels_802_11bg_checkBoxes(CheckBox checkbox) {
		    this.channels_802_11bg_checkBoxes.add(checkbox);
	 }
				 
	 public void setChannels_802_11bg_checkBoxesText(int i, String text){
	        this.channels_802_11bg_checkBoxes.get(i).setText(text);
	  }
				 
	 public void clearChannels_802_11bg_checkBoxes(){
	    	this.channels_802_11bg_checkBoxes.clear();
	 }
	
	 
	 //Accessors, mutators and methods for user's date, time and duration input as LocalTime and LocalDate
	 public LocalTime getTimeUserFrom(){
		 return timeUserFrom;
	 }
	 
	 public void setTimeUserFrom(int hour, int minute){
		 timeUserFrom = new LocalTime(hour, minute);
	 }
	 	 
	 public LocalDate getDateUserFrom(){
		 return dateUserFrom;
	 }
		
	 public void setDateUserFrom(int year, int month, int day){
		 dateUserFrom = new LocalDate(year, month, day);
	 }
	 
	 public float getDuration(){
		 return duration;
	 }
	 
	 public void setDuration(float duration){
		 this.duration = duration;
	 }
	 
	 
	//Accessor and mutator for merging the date and time user input into Joda DateTime
	 public DateTime getDateTimeUserFrom(){
		 return this.dateTimeUserFrom;
	 }
	 

	 public void setDateTimeUserFrom(){
		int hourOfDay = timeUserFrom.getHourOfDay();
		int minuteOfHour = timeUserFrom.getMinuteOfHour();
		
		int year = dateUserFrom.getYear();
		int monthOfYear = dateUserFrom.getMonthOfYear();
		int dayOfMonth = dateUserFrom.getDayOfMonth();
		
		DateTimeZone zoneUTC = DateTimeZone.UTC;
		dateTimeUserFrom = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour);
		dateTimeUserFrom = dateTimeUserFrom.toDateTime(zoneUTC);
	 }
	 
	//Accessor and mutator for merging the date and time user input into Joda DateTime
	 public DateTime getDateTimeUserUntil(){
		 return this.dateTimeUserUntil;
	 }
	 
	 public void setDateTimeUserUntil(){
		 int millis =  (int)(duration *  3600000);
	 	 dateTimeUserUntil = dateTimeUserFrom.plusMillis(millis);
	 }
	
	 
}
