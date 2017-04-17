package com.nitos.testbed.tools;


import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class ReserveResourcesFragment extends Fragment{

	 GlobalData appState;
	 View reserveNodesView;
	 
	 String data;
	 JSONArray componentsJSONArray = new JSONArray();
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState){
		 super.onCreate(savedInstanceState);
		 appState = ((GlobalData)getActivity().getApplicationContext());
	 }
	
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 
		 		reserveNodesView =   inflater.inflate(R.layout.reserve_resources_fragment, null);
		    						
				new JSONTestbedTask().execute();

		 		return reserveNodesView;
	   }
	 
	private class JSONTestbedTask extends AsyncTask<Void, Void, Void> {
	    	
			  
	    	   protected Void doInBackground(Void... arg0) {
	    		   
	    		   	JSONObject jsonObject = new JSONObject();
			    	JSONObject jsonAccount = new JSONObject();
	    		   	
			    	 try {
			    		 
			    		//add the selectedResources to the componentsJSONArray 
						fillComponentsArray(appState.getOrbitCheckBoxes(), appState.getOrbitAvailableNodes());
						fillComponentsArray(appState.getGridCheckBoxes(), appState.getGridAvailableNodes());
						fillComponentsArray(appState.getUsrpCheckBoxes(), appState.getUsrpAvailableNodes());
						fillComponentsArray(appState.getDisklessCheckBoxes(), appState.getDisklessAvailableNodes());
						fillComponentsArray(appState.getIcarusCheckBoxes(), appState.getIcarusAvailableNodes());
						fillComponentsArray(appState.getBaseStationCheckBoxes(), appState.getBaseStationsAvailable());
						fillComponentsArray(appState.getChannels_802_11a_checkBoxes(), appState.getChannels_802_11a_available());						
						fillComponentsArray(appState.getChannels_802_11bg_checkBoxes(), appState.getChannels_802_11bg_available());
						
						//The user has selected at least one resource
					    if(componentsJSONArray.length() != 0)
					    {
					    		jsonObject.put("name", UUID.randomUUID());
							
					    		jsonObject.put("valid_from", appState.getDateTimeUserFrom());
					    		jsonObject.put("valid_until", appState.getDateTimeUserUntil());
							
					    		jsonAccount.put("name","ardadouk");
					    		jsonObject.put("account", jsonAccount);		
						   
						    
					    		Log.i("ReserveNodesFragments jsonArray",componentsJSONArray.toString());
						    
					    		jsonObject.put("components", componentsJSONArray);
						    
					    		Log.i("ReserveNodesFragments jsonObject",jsonObject.toString());
					    		data = jsonObject.toString();
				    		
					    		//A Fake Trust Manager to allow all ssl connections	
					    		// FakeX509TrustManager.allowAllSSL();
			    			
					    		//  AssetManager assetManager = getActivity().getAssets();
					    		//clientFile = assetManager.open("user_cert.bks");
			    		    		   
								try {
									new TestbedHttpClient().setTestbedData("leases", data);
								} catch (UnrecoverableKeyException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (KeyStoreException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (NoSuchAlgorithmException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (CertificateException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					    }		
					
			 		} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        
		    		return null; 
	    	   }
	    	   
	    	   
	    	   /*It searches which resources the user selected through the checkboxes, and
	    	   *puts the uuid, taken from the TreeMap nodes to the componentsJSONArray
	    	   */
	    	   public void fillComponentsArray(ArrayList<CheckBox> checkBoxes, TreeMap<String, String> nodes){
	    		   
	    		   for(int i = 0; i < checkBoxes.size(); i++){
	    			   CheckBox checkBox = checkBoxes.get(i);
	    			   if(checkBox.isChecked()){
	    				   String uuid = nodes.get(checkBox.getText());
	    				   
	    				   JSONObject uuidJSONObject = new JSONObject();
						   try {
							uuidJSONObject.put("uuid", uuid);
						   } catch (JSONException e) {
							 // TODO Auto-generated catch block
							 e.printStackTrace();
						   }
						   componentsJSONArray.put(uuidJSONObject);
	    			   }
	    	     }
	          }
	    	   
	    		   
	   }
}
