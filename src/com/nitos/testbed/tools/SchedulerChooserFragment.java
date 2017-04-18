package com.nitos.testbed.tools;


import java.lang.reflect.Field;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.AdapterView.OnItemSelectedListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

	
public  class SchedulerChooserFragment extends Fragment   implements OnClickListener{

		View schedulerChooserView;
		TextView textDisplayTime;
		TextView textDisplayDate;
	    
		Spinner spinnerDuration;
		    
		//HashMaps and Arraylists for Nodes have been declared globally in GlobalData class
		GlobalData appState;

		public final static int GET = 1;
	    
		
	 	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	    	
	    		schedulerChooserView= inflater.inflate(R.layout.scheduler_chooser_fragment, container,false);
			//get global data
			appState = ((GlobalData)getActivity().getApplicationContext()); 
			
			//LocalDateTime, current date, and time 00:00 are displayed on the SchedulerChooserFragment's view
			showCurrentLocalDateTime();
			showCurrentDateOnView();
			showTimeOnView();
						
			
			Button btnDate = (Button) schedulerChooserView.findViewById(R.id.buttonSelectDate);
			Button btnTime = (Button) schedulerChooserView.findViewById(R.id.buttonSelectTime);
			Button btnCheckAvailableResources = (Button)schedulerChooserView.findViewById(R.id.btnCheckAvailableResources);
		 	btnDate.setOnClickListener(this);
		    	btnTime.setOnClickListener(this);
		    	btnCheckAvailableResources.setOnClickListener(this);
		    
			String duration[] = {"0.5","1","2","2.5","3", "3.5","4"};
		    
			spinnerDuration = (Spinner)schedulerChooserView.findViewById(R.id.spinnerDuration);
			ArrayAdapter<String> spinnerAdapter;
			spinnerAdapter= new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.spinner_item, duration);
			spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinnerDuration.setAdapter(spinnerAdapter);
		
		    
			spinnerDuration.setOnItemSelectedListener(new OnItemSelectedListener()
			{
				public void onItemSelected(AdapterView<?> arg0, View arg1,
 									int arg2, long arg3) {
					appState.setDuration(Float.parseFloat(spinnerDuration.getSelectedItem().toString()));                   
				}
				public void onNothingSelected(AdapterView<?> arg0) {
                                // TODO Auto-generated method stub                 
				}
			});
		    
			return schedulerChooserView;
		}
	    
	    
	   
		// display current date and time
		public void showCurrentLocalDateTime() {
			TextView textViewLocalTime = (TextView) schedulerChooserView.findViewById(R.id.textViewLocalTime);
	 		 
			DateTime localDateTime = new DateTime();
	 		 
			Log.i("localDatetime", localDateTime.toString());
	 		 
			//We should split the date and time with T and +
			String splits[] = localDateTime.toString().split("T");
			String localDate = splits[0] + " ";
	 		 
			Log.i("splits 1", splits[1]);
			String splitsTimeWithoutZone[] = splits[1].split("\\+");
			String splitsTimeWithoutSecs[] = splitsTimeWithoutZone[0].split("\\.");
			String localTime = splitsTimeWithoutSecs[0];
	 		 
			textViewLocalTime.setText(localDate + localTime);
		}
	    	       
		// display current date
		public void showCurrentDateOnView() {
			textDisplayDate = (TextView) schedulerChooserView.findViewById(R.id.textDate);
	
			// Use the current date as the default date in the picker
			DateTime dt = new DateTime();
			int day = dt.getDayOfMonth();
			int year = dt.getYear();
			int month = dt.getMonthOfYear();
		        
			Log.i("Scheduler Chooser Fragment day month year", day +" "+ month + " "+ year + "");
		        
			//Initiate LocalDate dateUserFrom in GlobalData
			appState.setDateUserFrom(year, month, day);
		        
			// set current date into textview
			textDisplayDate.setText(year +"-"+ String.format("%02d", month) +"-" + String.format("%02d", day));
	 	 
	 	 
		} 
	 		
		// display time
		public void showTimeOnView() { 
			textDisplayTime = (TextView) schedulerChooserView.findViewById(R.id.textTime);
				
			int hours = 0;
			int minutes = 0;
			//Initiate timeUserFrom in GlobalData
			appState.setTimeUserFrom(hours, minutes);
			// set  time into textview
			textDisplayTime.setText(String.format("%02d", hours) +":"+ String.format("%02d", minutes)); 
		}
	 		
	 		

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
				case R.id.buttonSelectDate:
					DialogFragment dateFragment = new DatePickerFragment(textDisplayDate);
					dateFragment.show(getChildFragmentManager(), "datePicker");
				break;
				case R.id.buttonSelectTime:
					DialogFragment timeFragment = new TimePickerFragment(textDisplayTime);
					timeFragment.show(getChildFragmentManager(), "timePicker");
				break;
				case R.id.btnCheckAvailableResources:
					// Calling async task to get json
					appState.setDateTimeUserFrom();
					appState.setDateTimeUserUntil();
			 		
					if (isNetworkConnected()){
						DateTimeZone zoneUTC = DateTimeZone.UTC;
						DateTime dateTimeUTC = new DateTime(zoneUTC);
						Log.i("datetime utc",dateTimeUTC.toString());
						Log.i("user time", appState.getDateTimeUserFrom()+ "");
						if(appState.getDateTimeUserFrom().isBefore(dateTimeUTC)){
		        		
							int duration = Toast.LENGTH_SHORT;
							Toast.makeText(schedulerChooserView.getContext(), "Date and Time input is before now", duration).show();
						}
						else
							new JSONTestbedTask().execute();
					} else {
		        			int duration = Toast.LENGTH_SHORT;

		        			Toast.makeText(schedulerChooserView.getContext(), "No network connection", duration).show();
		        		}
		        		break;
		        	}
				
			}
			
			//This function checks if there is a network connection			
			public boolean isNetworkConnected(){
				ConnectivityManager cm =
			    	        (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			    	 
				NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
				boolean isConnected = activeNetwork != null &&
					activeNetwork.isConnectedOrConnecting();
				return isConnected;
			}
			
			  
			//Fix error for nested fragments, taken from stackoverflow, it may not needed
			@Override
			public void onDetach() {
				super.onDetach();

				try {
					Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
					childFragmentManager.setAccessible(true);
					childFragmentManager.set(this, null);
				} catch (NoSuchFieldException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
			
					
			private class JSONTestbedTask extends AsyncTask<Void, Void, Void> {
				
				ProgressDialog progressDialog;

				
				@Override
				protected void onPreExecute(){ 
					super.onPreExecute();
					progressDialog = new ProgressDialog(schedulerChooserView.getContext());
			                
					progressDialog.setMessage("Please wait...");
					progressDialog.show();    
				}
			    	
				protected Void doInBackground(Void... arg0) {
					//A Fake Trust Manager to allow all ssl connections	
					FakeX509TrustManager.allowAllSSL();
		    	
					Log.i("JSONTestbedTask", "doInBackground");
		    		   
					String jsonNodesStr = null;
					String jsonChannelsStr = null;
					String jsonLeasesStr = null;
		    		   	
					try {
		    		   		
						//Get resources/nodes json
						jsonNodesStr = ( (new TestbedHttpClient()).getTestbedData("nodes"));
						//Get resources/channels json
						jsonChannelsStr = ( (new TestbedHttpClient()).getTestbedData("channels"));
						//Get resources/leases json
						jsonLeasesStr = ( (new TestbedHttpClient()).getTestbedData("leases"));
			    		   		
			    		 	//In case we want hardcoded data, we have the data stored locally in files
		    		   	/*	
		    		   		InputStream inputNodes = getResources().openRawResource(R.raw.nodes_json);
		    		   		jsonNodesStr = new java.util.Scanner(inputNodes).useDelimiter("\\A").next();
		    		   		InputStream inputChannels = getResources().openRawResource(R.raw.channels_json);
		    		   		jsonChannelsStr = new java.util.Scanner(inputChannels).useDelimiter("\\A").next();
		    		   		InputStream inputLeases = getResources().openRawResource(R.raw.leases_json);
		    		   		jsonLeasesStr = new java.util.Scanner(inputLeases).useDelimiter("\\A").next();
		    		   		
		    		   		Log.i("JSONTestbedTask", "strngs passed");
		    		   		try {
		    		   			inputNodes.close();
		    		   			inputLeases.close();
		    		   			inputChannels.close();
		    		   		} catch (IOException e) {
		    		   			// TODO Auto-generated catch block
		    		   			e.printStackTrace();
		    		   		}
										*/		
			    		 	
						//Parse Nodes, Channels and Leases JSON
						JSONTestbedParser jsonParser = new JSONTestbedParser();
		    		   		
						if(jsonNodesStr != null) {
							jsonParser.setNodes(jsonNodesStr);
						}
						else {
Log.e("ServiceHandler", "Couldn't get any data from the url");
		    		   		}
		    		   		
		    		   		if(jsonChannelsStr != null) {
		    		   			jsonParser.setChannels(jsonChannelsStr);
		    		   		}
		    		   		else {
		    		   			Log.e("ServiceHandler", "Couldn't get any data from the url");
		    		   		}
		    		   		
		    		   		if (jsonLeasesStr != null) {
		    		   		    jsonParser.setLeases(jsonLeasesStr);	    		   		
		    		   		} else {
		    		   			Log.e("ServiceHandler", "Couldn't get any data from the url");
		    		   		}
		    		   		
		    		   } catch (JSONException e) {
		    		   		
				   			e.printStackTrace();
			   		   }
		    		   
		             
		    		   return null; 
		    	   }
		    	   
		    	   //After taking all the information of the resources, we call the AvailableResourcesFragment
		    	   protected void onPostExecute(final Void unused) {
		    		    progressDialog.dismiss();
		    		   
		    		    final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
			            ft.replace(R.id.layoutToReplace_nitosScheduler, new AvailableResourcesFragment()); 
			            ft.addToBackStack(null);
			            ft.commit();
		    	   }
		    	   
		    
		    	   
		    }
			
		
		    
		    
		    
		    private class JSONTestbedParser {
		    	
		    	public void setNodes(String jsonNodesStr) throws JSONException  {
		    		 	JSONObject jsonNodesObj = new JSONObject(jsonNodesStr);
		    		
		    		 	JSONObject resource_response_obj = jsonNodesObj.getJSONObject(Constants.TAG_RESOURCE_RESPONSE);
				
						JSONArray resources = resource_response_obj.getJSONArray(Constants.TAG_RESOURCES);
		          			
						Log.i("JSONTestbedParser", "set Nodes");
					
						ArrayList<String> check_for_node_names_duplicates = new ArrayList<String>();
						// looping through All
					    for (int node_i = 0; node_i < resources.length(); node_i++) {
					    	JSONObject nodeObj = resources.getJSONObject(node_i);
							
							Log.i("JSONTestbedParser node name", nodeObj.getString("name"));
							
							String hardware_type = nodeObj.getString("hardware_type");
					        String node_name = nodeObj.getString("name");
							String uuid = nodeObj.getString("uuid");
							
							//A node can be contained two times 
							if(check_for_node_names_duplicates.contains(node_name)){
						    	Log.w("duplicate node","");
						    	continue;
						    }
						    else{
						    	check_for_node_names_duplicates.add(node_name);
						    }
					       
                            if(hardware_type.equals("PC-Orbit")){
                            	Log.i("JSONParser setNodes", "orbitNodes");
                            	ResourcesData resourcesData = new ResourcesData(uuid);
								appState.getOrbitNodes().put(node_name, resourcesData);
							}
							else if(hardware_type.equals("PC-Grid")){
								Log.i("JSONParser setNodes", "gridNodes");
								ResourcesData resourcesData = new ResourcesData(uuid);
								appState.getGridNodes().put(node_name, resourcesData);
								
							}
							else if(hardware_type.equals("PC-USRP")){
								Log.i("JSONParser setNodes", "usrpNodes");
								ResourcesData resourcesData = new ResourcesData(uuid);
								appState.getUsrpNodes().put(node_name, resourcesData);
							}
							else if(hardware_type.equals("PC-Diskless")){
								Log.i("JSONParser setNodes", "disklessNodes");
								ResourcesData resourcesData = new ResourcesData(uuid);
								appState.getDisklessNodes().put(node_name, resourcesData);
							}
							else if(hardware_type.equals("PC-Icarus")) {	
								Log.i("JSONParser setNodes", "icarus");
								ResourcesData resourcesData = new ResourcesData(uuid);
								appState.getIcarusNodes().put(node_name, resourcesData);
							}
							else if(hardware_type.equals("PC-BaseStations")) {	
								Log.i("JSONParser setNodes", "Base Stations");
								ResourcesData resourcesData = new ResourcesData(uuid);
								appState.getBaseStations().put(node_name, resourcesData);
							}
							else {
								Log.w("Hardware Type", "unknown");
							}
			
						}	   		
		    		
		    	}	 
		    	
		    	public void setChannels(String jsonChannelsStr) throws JSONException  {
		    	
		    		JSONObject jsonChannelsObj = new JSONObject(jsonChannelsStr);
		    		
	    		 	JSONObject resource_response_obj = jsonChannelsObj.getJSONObject(Constants.TAG_RESOURCE_RESPONSE);
			
					JSONArray resources = resource_response_obj.getJSONArray(Constants.TAG_RESOURCES);
	          			
					Log.i("JSONTestbedParser", "set Channels");
					
					ArrayList<String> check_for_channel_names_duplicates = new ArrayList<String>();
					// looping through All Leases
					for (int channel_i = 0; channel_i < resources.length(); channel_i++) {
					
						    JSONObject channelObj = resources.getJSONObject(channel_i);
						    
						    Log.i("JSONTestbedParser channel name", channelObj.getString("name"));
							
					        String channel_name = channelObj.getString("name");
							String uuid = channelObj.getString("uuid");
							
							//A node can be contained two times 
							if(check_for_channel_names_duplicates.contains(channel_name)){
						    	Log.w("duplicate channel","");
						    	continue;
						    }
						    else{
						    	check_for_channel_names_duplicates.add(channel_name);
						    }
				
							if((channel_name.compareTo(Constants.LOWER_CHANNEL_802_11a) > 0 && Constants.UPPER_CHANNEL_802_11a.compareTo(channel_name) < 0) ||
								channel_name.compareTo(Constants.LOWER_CHANNEL_802_11a) == 0 || channel_name.compareTo(Constants.UPPER_CHANNEL_802_11a) == 0 ) {								
                            	ResourcesData resourcesData = new ResourcesData(uuid);
								appState.channels_802_11a.put(channel_name, resourcesData);
								Log.i("Channel",channel_name + " - " + "1-13");
							}
							else if(channel_name.compareTo(Constants.LOWER_CHANNEL_802_11bg) > 0 && Constants.UPPER_CHANNEL_802_11bg.compareTo(channel_name) < 0 ||
									channel_name.compareTo(Constants.LOWER_CHANNEL_802_11bg) == 0 || channel_name.compareTo(Constants.UPPER_CHANNEL_802_11bg) == 0 ){
								ResourcesData resourcesData = new ResourcesData(uuid);
								appState.channels_802_11bg.put(channel_name, resourcesData);
								Log.i("Channel",channel_name + " - " + "36-40");
							}
							else{
								Log.w("Channel Type", "unknown");
							}
							
					}
						
				
				
		    	}
		    	
		    	public void setLeases(String jsonLeasesStr) throws JSONException  {
		    		    JSONObject jsonLeasesObj = new JSONObject(jsonLeasesStr);
		            
						JSONObject resource_response_obj = jsonLeasesObj.getJSONObject(Constants.TAG_RESOURCE_RESPONSE);
						// Getting JSON Array Leases
						JSONArray resources = resource_response_obj.getJSONArray(Constants.TAG_RESOURCES);
		     
						Log.i("JSONTestbedParser", "set Leases");
		      
						// looping through All Leases
						for (int lease_i = 0; lease_i < resources.length(); lease_i++) {
						
							    JSONObject leaseObj = resources.getJSONObject(lease_i);
							    
							    //Ignore the leases with status = past and status = cancelled
							    String leaseStatus = leaseObj.getString("status");
							    if(leaseStatus.equals("past") || leaseStatus.equals("cancelled"))
							    	continue;
								
								//set the reservation(date and time, from and until)  in each node of HashMap
								Reservation reservation = setTimeAndDateOnReservation(leaseObj);
							    //Get all the components in each lease
								JSONArray componentsArray = leaseObj.getJSONArray(Constants.TAG_COMPONENTS);
								
								ArrayList<String> check_for_resources_names_duplicates = new ArrayList<String>();
							    //Looping through all components and add the reservation to the ArrayList of the appropriate node of the HashMap
								for(int component_i = 0; component_i < componentsArray.length(); component_i++)	{
									JSONObject componentObj = componentsArray.getJSONObject(component_i).getJSONObject(Constants.TAG_COMPONENT);
									Log.i("component name ", componentObj.getString("name"));
									
									String resource_name = componentObj.getString("name");
									
									//A node can be contained two times in the components array
									if(check_for_resources_names_duplicates.contains(resource_name)){
								    	//NA PSAKSW GIA THROWS EXCEPTION
								    	Log.i("duplicate Leases","duplicate");
								    	continue;
								    }
								    else{
								    	check_for_resources_names_duplicates.add(resource_name);
								    }
									
									//Add the reservation object to the ArrayList where node belongs.
									
									if(appState.getOrbitNodes().containsKey(resource_name)){
										Log.i("JSONParser setLeases", "grid");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getOrbitNodes().get(resource_name);
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getOrbitNodes().put(resource_name, resourcesData);
									}
									else if(appState.getGridNodes().containsKey(resource_name)){
										Log.i("JSONParser setLeases", "grid");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getGridNodes().get(resource_name);
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getGridNodes().put(resource_name, resourcesData);
									}
									else if(appState.getUsrpNodes().containsKey(resource_name)) {
										Log.i("JSONParser setLeases", "usrp");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getUsrpNodes().get(resource_name);
										if(resourcesData == null)
											Log.i("nodesData","null");
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getUsrpNodes().put(resource_name, resourcesData);
									}
									else if(appState.getDisklessNodes().containsKey(resource_name)) {
										Log.i("JSONParser setLeases", "diskless");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getDisklessNodes().get(resource_name);
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getDisklessNodes().put(resource_name, resourcesData);
									}
									else if(appState.getIcarusNodes().containsKey(resource_name)) {
										Log.i("JSONParser setLeases", "icarus");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getIcarusNodes().get(resource_name);
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getIcarusNodes().put(resource_name, resourcesData);
									}
									else if(appState.getBaseStations().containsKey(resource_name)){
										Log.i("JSONParser setLeases", "Base STations");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getBaseStations().get(resource_name);
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getBaseStations().put(resource_name, resourcesData);
									}
									else if(appState.getChannels802_11a().containsKey(resource_name)){
										Log.i("JSONParser setLeases", "channel 802_11a");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getChannels802_11a().get(resource_name);
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getChannels802_11a().put(resource_name, resourcesData);
									}
									else if(appState.getChannels802_11bg().containsKey(resource_name)){
										Log.i("JSONParser setLeases", "channel 802_11bg");
										//Get nodesData which contains uuid value and add the reservation
										ResourcesData resourcesData = appState.getChannels802_11bg().get(resource_name);
										resourcesData.setReservations(reservation);
										//overwrite the entry of the HashMap
										appState.getChannels802_11bg().put(resource_name, resourcesData);
									}
								    else {
										Log.w("Node name", "unknown");
									
									}
								}
								check_for_resources_names_duplicates.clear();
						}	   			
		    	}	
		    	
		    	
		    	public Reservation setTimeAndDateOnReservation(JSONObject leaseObj) throws JSONException{
		   	
		    		
		    		Log.i("JSONTestbedParser", "set time and date");
		    		    		
		    		String valid_from = leaseObj.getString("valid_from");
					Log.i("valid_from is", valid_from);
				
					String valid_until = leaseObj.getString("valid_until");
					Log.i("valid_until is", valid_until);
					
					Reservation reservation = new Reservation(valid_from, valid_until);
							
					return reservation;
		   	 	}
		    }
		    
		    
		  
	 		
	 		
	}

