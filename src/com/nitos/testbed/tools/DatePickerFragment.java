package com.nitos.testbed.tools;


import org.joda.time.DateTime;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

	@SuppressLint("ValidFragment")
	TextView txtDate;
	GlobalData appState;
	
	
	public DatePickerFragment(TextView txtDate) {
		super();
		this.txtDate = txtDate;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		DateTime dt = new DateTime();
	      
		int year = dt.getYear();
		int month = dt.getMonthOfYear()-1;
		int day = dt.getDayOfMonth();
	        
		appState = ((GlobalData)getActivity().getApplicationContext()); 
		appState.setDateUserFrom(year, month, day);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		appState.setDateUserFrom(year, month + 1 , day);

		txtDate.setText(new StringBuilder().append(year)
	 			   .append("-").append(String.format("%02d", month + 1) ).append("-").append(String.format("%02d", day)));
	}	
}
