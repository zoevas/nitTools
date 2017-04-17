package com.nitos.testbed.tools;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;


@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment  implements TimePickerDialog.OnTimeSetListener{

public static final int TIME_PICKER_INTERVAL=30;
private boolean mIgnoreEvent = false;
GlobalData appState;	


	
@SuppressLint("ValidFragment")
TextView txtTime;
	
	
	
	public TimePickerFragment(TextView txtTime) {
		super();
		this.txtTime = txtTime;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	      
		  
		    // Use the current time as the default values for the picker
		    DateTimeZone zone = DateTimeZone.forID("Europe/Athens");
	        DateTime dt = new DateTime(zone);
	        int hours = 0;
	        int minutes = 0;
	        
	        appState = ((GlobalData)getActivity().getApplicationContext()); 
	        appState.setTimeUserFrom(hours, minutes);
	        
	        
	        // Create a new instance of TimePickerDialog and return it
	        TimePickerDialog time = new TimePickerDialog(getActivity(), this, hours, minutes,
	                DateFormat.is24HourFormat(getActivity()))
	        {

	            @Override
	            public void onTimeChanged(TimePicker view, int hours, int minutes) {
	                // TODO Auto-generated method stub
	            	super.onTimeChanged(view, hours, minutes);
	 	            this.setTitle("Select Time");
	 	            if (!mIgnoreEvent){
	 	                minutes = getRoundedMinute(minutes);
	 	                mIgnoreEvent=true;
	 	                view.setCurrentMinute(minutes);
	 	                mIgnoreEvent=false;
	 	            }

	            }
	 	           
	         };
	        
	        return time;

	    }

	        
	     public static int getRoundedMinute(int minutes){
	            if(minutes % TIME_PICKER_INTERVAL != 0){
	               int minuteFloor = minutes - (minutes % TIME_PICKER_INTERVAL);
	               minutes = minuteFloor + (minutes == minuteFloor + 1 ? TIME_PICKER_INTERVAL : 0);
	               if (minutes == 60)  minutes=00;
	            }

	           return minutes;
	     }
	        
	    public void onTimeSet(TimePicker view, int hours, int minutes) {

	    	appState.setTimeUserFrom(hours, minutes);
	        
	        Log.i("Time", "Time changed.");
	        txtTime.setText(String.format("%02d", hours) +":"+ String.format("%02d", minutes));
	    }
	    
	   
}
