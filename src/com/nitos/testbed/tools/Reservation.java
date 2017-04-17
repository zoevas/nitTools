package com.nitos.testbed.tools;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import android.util.Log;


public class Reservation {

	   DateTime dateTimeFrom;
	   DateTime dateTimeUntil;
	   	  
	   
	   public Reservation(String valid_from, String valid_until){
		 
		    DateTimeZone zoneUTC = DateTimeZone.UTC;
		    dateTimeFrom = new DateTime(valid_from, zoneUTC);
	  		Log.w("Reservation", "" + dateTimeFrom.getZone());
	  		Log.i("Reservation", dateTimeFrom.toString());
	  		dateTimeUntil = new DateTime(valid_until, zoneUTC);
	  		Log.i("Reservation", dateTimeUntil.toString());
	   }
	  
	   
	   public DateTime getDateTimeFrom(){
		   return dateTimeFrom;
		   
	   }
	   
	   public DateTime getDateTimeUntil(){
		   return dateTimeUntil;
	   }
}
