package com.nitos.testbed.tools;

/**
 * 
 * @author zoi
 * This class contains all the constants of the program
 */
public class Constants {

	public static final String BASE_URL = "https://xx.xxx.xx.xx:8001/resources/";
	//Strings for the SharedPreferences 
	public static final String CHECKBOXES_PREFERENCES = "CHECKBOXES_PREFERENCES";
	public static final String LOGINPREFS = "LOGINPREFS";
	
	//The channels from 1 to 13 are 802_11a
	public static final String LOWER_CHANNEL_802_11a = "1";
	public static final String UPPER_CHANNEL_802_11a = "13";
	
	//The channels from 36 to 140 re 802_11bg
	public static final String LOWER_CHANNEL_802_11bg = "36";
	public static final String UPPER_CHANNEL_802_11bg = "140";
	
	public enum HardwareType {
	     ORBIT, GRID, USRP, DISKLESS, ICARUS,BASE_STATIONS,CHANNELS_802_11A, CHANNELS_802_11BG
  	 }
	
	//TAGS for get JSON Objects and Arrays
    public static final String TAG_RESOURCE_RESPONSE = "resource_response";
    public static final String TAG_RESOURCES= "resources";
    public static final String TAG_COMPONENTS= "components";
    public static final String TAG_COMPONENT= "component";
}
