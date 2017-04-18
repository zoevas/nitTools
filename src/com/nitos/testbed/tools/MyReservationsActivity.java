package com.nitos.testbed.tools;



import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/*This activity is inflated from the user's action bar's icon
  It displays all the user's reservations. THe user can cancel them but this is not yet implemented.
*/
public class MyReservationsActivity extends MainMenuActivity{
	
	//HashMaps and Arraylists for Nodes have been declared globally in GlobalData class
    GlobalData appState;
    LinearLayout linearLayout;
    ScrollView scrollView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
 		super.onCreate(savedInstanceState);
   	 	  
		appState = ((GlobalData)getApplicationContext()); 
		
		scrollView=new ScrollView(this);
		linearLayout=new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setPadding(10, 10, 8, 8);
		scrollView.addView(linearLayout);
		 

		if (isNetworkConnected()){
			new JSONTestbedReadLeasesTask().execute();
		} else {
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(MyReservationsActivity.this, "No network connection", duration).show();
 		}
		this.setContentView(scrollView);		
	 }	 
	
	public boolean isNetworkConnected(){
		ConnectivityManager cm =
	    	        (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	    	 
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&
	    	                      activeNetwork.isConnectedOrConnecting();
		return isConnected;
	}
	 


	private class JSONTestbedReadLeasesTask extends AsyncTask<Void, Void, Void> {
		String jsonLeasesStr  = null;
		ArrayList<String> accounts;
		JSONTestbedParser jsonParser;

		private ProgressDialog progressDialog;

		 
		@Override
		protected void onPreExecute(){ 
			super.onPreExecute();
			//Progress dialog with a waiting message
			progressDialog = new ProgressDialog(MyReservationsActivity.this);
			                
			progressDialog.setMessage("Please wait...");
			progressDialog.show();    
		}
			   
		protected Void doInBackground(Void... arg0) {
			FakeX509TrustManager.allowAllSSL();
			    	
			Log.i("JSONTestbedReadLeasesTask", "doInBackground");
	    		   	
			jsonParser = new JSONTestbedParser();
	    		   	
			accounts = jsonParser.getAccounts();

			//Get resources/leases json
			jsonLeasesStr = ( (new TestbedHttpClient()).getTestbedData("leases"));

			return null;
		}
	    	   
		protected void onPostExecute(final Void unused) {
			progressDialog.dismiss();
			try {
				jsonParser.printLeases(jsonLeasesStr, accounts);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    		   	
		}
	}
		
		
		  
	private class JSONTestbedParser  implements OnClickListener{
	    	
		public ArrayList<String> getAccounts() {
			ArrayList<String> accounts = new ArrayList<String>();
			accounts.add("ardadouk");
			return accounts;
		}
	    	
		/* It finds which leases belong to the user's account, and it prints the resources*/
		public void printLeases(String jsonLeasesStr, ArrayList<String> accounts) throws JSONException{
			JSONObject jsonLeasesObj = new JSONObject(jsonLeasesStr);
		            
			JSONObject resource_response_obj = jsonLeasesObj.getJSONObject(Constants.TAG_RESOURCE_RESPONSE);
			// Getting JSON Array Leases
			JSONArray resources = resource_response_obj.getJSONArray(Constants.TAG_RESOURCES);
	     
			Log.i("JSONTestbedParser", "print Leases");
	      
			for(String account : accounts) {
				// looping through All Leases
				for (int lease_i = 0; lease_i < resources.length(); lease_i++) {
					JSONObject leaseObj = resources.getJSONObject(lease_i);
						    
					//Ignore the leases with status = past and status = cancelled
					String leaseStatus = leaseObj.getString("status");
					if(leaseStatus.equals("past") || leaseStatus.equals("cancelled"))
						continue;
						    
					String account_name_in_lease = leaseObj.getJSONObject("account").getString("name");
						    
					if(account.equals(account_name_in_lease)){				
						TextView textViewReservation = new TextView(MyReservationsActivity.this);
						textViewReservation.setTypeface(null, Typeface.BOLD);
						String valid_from = leaseObj.getString("valid_from");
						DateTimeZone zoneUTC = DateTimeZone.UTC;
						DateTime dateTimeFrom = new DateTime(valid_from);
										
						String valid_until = leaseObj.getString("valid_until");
						DateTime dateTimeUntil = new DateTime(valid_until);
						String lease_date_time_info =  "RESERVATION STARTING AT " + dateTimeFrom.toString("MM-dd-yyyy HH:mm:ss") + " AND ENDING AT " + dateTimeUntil.toString("MM-dd-yyyy HH:mm:ss");
						textViewReservation.setText(lease_date_time_info + "\n");
						linearLayout.addView(textViewReservation);
										
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
								Log.i("duplicate Leases","duplicate");
								continue;
							} else {
								check_for_resources_names_duplicates.add(resource_name);
							}
												
												
							TextView textViewResource = new TextView(MyReservationsActivity.this);											
							textViewResource.setText(resource_name + "\n");
							linearLayout.addView(textViewResource);
								
							Button cancelButton = new Button(MyReservationsActivity.this);
							LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

							cancelButton.setText("cancel");
							cancelButton.setTag(componentObj.getString("uuid"));
							cancelButton.setOnClickListener(this);
							linearLayout.addView(cancelButton, layoutParams);
										
							//Add the reservation object to the ArrayList where node belongs.
																		//http://androiddesk.wordpress.com/2012/08/05/creating-dynamic-views-in-android/
							//http://stackoverflow.com/questions/1851633/how-to-add-button-dynamically-in-android
							//http://stackoverflow.com/questions/14808374/setting-button-id-programatically
							//http://stackoverflow.com/questions/5291726/what-is-the-main-purpose-of-settag-gettag-methods-of-view
						}	
						check_for_resources_names_duplicates.clear();
									
					}
				}
			}
		}
	    
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
	    		//If the button cancel is clicked, we will get the uuid of the resource via the getTag and we will send DELETE request
			Log.i("Tags" , v.getTag() + "");				
				//dosth(v.getTag())
		}
	    	
	}
	    	
}
