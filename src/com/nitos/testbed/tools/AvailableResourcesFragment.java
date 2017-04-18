package com.nitos.testbed.tools;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/*This Fragment belongs to NitosSchedulerActivity. It finds which resources are available each
 * time a listview item is checked. It has the CheckResourcesFragment as nested fragment which displays all the available resources in the form 
 * of checkboxes.*/
public class AvailableResourcesFragment extends Fragment  implements OnClickListener{

		View slidingView;
		GlobalData appState;
	 
	 
		ListView nodesListView;
	 
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			appState = ((GlobalData)getActivity().getApplicationContext());
		}
	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	   	    	// Inflate the layout for this fragment
				slidingView =  inflater.inflate(R.layout.available_resources_fragment, null);
				SlidingPaneLayout pane = (SlidingPaneLayout)slidingView.findViewById(R.id.sliding_pane_layout);
				pane.openPane();		 		
		 		
				//Set date and time given by user to ekana st scheduler chooser fragment		 		
				/*appState.setDateTimeUserFrom();
				appState.setDateTimeUserUntil();*/
				Log.i("AvailableNodesFragment DateTimeUserFrom", appState.getDateTimeUserFrom().toString());
				Log.i("AvailableNodesFragment DateTimeUserUntil", appState.getDateTimeUserUntil().toString());
		 		
				ListView nodesListView = (ListView) slidingView.findViewById(R.id.nodes_list);
		 		
				//Make the ListView with all nodes categories
				String[] items = { getResources().getString(R.string.orbit_nodes),
						getResources().getString(R.string.grid_nodes),
						getResources().getString(R.string.usrp_nodes),
						getResources().getString(R.string.diskless_nodes), 
						getResources().getString(R.string.icarus_nodes) ,
						getResources().getString(R.string.base_station),
						getResources().getString(R.string.channels_802_11a),
						getResources().getString(R.string.channels_802_11bg)
				};

				ArrayAdapter<String> adapt = new ArrayAdapter<String>(getActivity(), R.layout.nodes_categories_listview_item, items);
				nodesListView.setAdapter(adapt);
		 			 
				nodesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
				@Override
				public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
   						TextView textView = (TextView) itemClicked;
						String strText = textView.getText().toString();
						Bundle args = new Bundle();
			                   
			                 
						if (strText.equalsIgnoreCase(getResources().getString(R.string.icarus_nodes))) {
							Log.i("AVailableNodes icarus checkboxes size",appState.getIcarusCheckBoxes().size() +"" );
			                	    
							checkAvailableResources(Constants.HardwareType.ICARUS);
							args.putString("resource_type",Constants.HardwareType.ICARUS.toString());    
			                	   
							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
					                   	  
							fragmentTransaction.replace(R.id.child_fragmentB, checkResourcesFragment);
			       		 		   
							checkResourcesFragment.setArguments(args);
			       		 		
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit();
						} else  if (strText.equalsIgnoreCase(getResources().getString(R.string.orbit_nodes))) {	
							checkAvailableResources(Constants.HardwareType.ORBIT);
			                	   
							args.putString("resource_type",Constants.HardwareType.ORBIT.toString()); 
			                	    
							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
			                	   
							fragmentTransaction.replace(R.id.child_fragmentB,  checkResourcesFragment);
			       		 		   
							checkResourcesFragment.setArguments(args);
			       		 		
							fragmentTransaction.addToBackStack(null);
			       		 	fragmentTransaction.commit();
						} else if (strText.equalsIgnoreCase(getResources().getString(R.string.grid_nodes))) {
							checkAvailableResources(Constants.HardwareType.GRID);
			                	   
							args.putString("resource_type",Constants.HardwareType.GRID.toString()); 
			                	   
 							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
							fragmentTransaction.replace(R.id.child_fragmentB,  checkResourcesFragment);
			       		 		   
							checkResourcesFragment.setArguments(args);
			       		 		
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit();
			                	   
						} else if (strText.equalsIgnoreCase(getResources().getString(R.string.usrp_nodes))) {
							checkAvailableResources(Constants.HardwareType.USRP);
			                	   
							args.putString("resource_type",Constants.HardwareType.USRP.toString()); 
                                    
							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
			                	   
							fragmentTransaction.replace(R.id.child_fragmentB,  checkResourcesFragment);
			       		 		   
							checkResourcesFragment.setArguments(args);
			       		 		
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit(); 
						} else if (strText.equalsIgnoreCase(getResources().getString(R.string.diskless_nodes))) {
							checkAvailableResources(Constants.HardwareType.DISKLESS);
				               	   
							args.putString("resource_type",Constants.HardwareType.DISKLESS.toString()); 
	                                
							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
				                	   
							fragmentTransaction.replace(R.id.child_fragmentB,  checkResourcesFragment);
				       		 		   
							checkResourcesFragment.setArguments(args);
				       		 		
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit(); 
			                	   
						} else if (strText.equalsIgnoreCase(getResources().getString(R.string.base_station))) {
							checkAvailableResources(Constants.HardwareType.BASE_STATIONS);
				                	   
							args.putString("resource_type", Constants.HardwareType.BASE_STATIONS.toString()); 
	                                 
							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
				                	   
							fragmentTransaction.replace(R.id.child_fragmentB,  checkResourcesFragment);
				       		 		   
							checkResourcesFragment.setArguments(args);
				       		 		
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit();  
			                	   
						} else if (strText.equalsIgnoreCase(getResources().getString(R.string.channels_802_11a))) {
							checkAvailableResources(Constants.HardwareType.CHANNELS_802_11A);
							
							args.putString("resource_type", Constants.HardwareType.CHANNELS_802_11A.toString()); 
	                                 
							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
							fragmentTransaction.replace(R.id.child_fragmentB,  checkResourcesFragment);
				       		 		   
							checkResourcesFragment.setArguments(args);
				       		 		
							fragmentTransaction.addToBackStack(null);
						} else if (strText.equalsIgnoreCase(getResources().getString(R.string.channels_802_11bg))){
							checkAvailableResources(Constants.HardwareType.CHANNELS_802_11BG);
				                	   
							args.putString("resource_type", Constants.HardwareType.CHANNELS_802_11BG.toString()); 
	                                 
							CheckResourcesFragment checkResourcesFragment = new CheckResourcesFragment();
							FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
				                	   
							fragmentTransaction.replace(R.id.child_fragmentB,  checkResourcesFragment);
				       		 		   
							checkResourcesFragment.setArguments(args);
				       		 		
							fragmentTransaction.addToBackStack(null);
							fragmentTransaction.commit();  
						}
						itemClicked.setSelected(true);
					}
				});
		 		
				Button btnReserve = (Button) slidingView.findViewById(R.id.btnReserveNodes);
				btnReserve.setOnClickListener(this);
		 		
				return slidingView;
		}
	 
	 
	//This code is taken from stackOverflow. It fixes a bug that occurs in nested fragments.
	@Override
	public void onDetach() {
		super.onDetach();

		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void onDestroy(){
		super.onDestroy();
	}
	    
	public void onPause(){
		super.onPause();
		Log.i("AvailableResourcesFragment","onPause");
		Log.i("AvailableResourcesFragment","onDestroy");
		SharedPreferences nodesSharedPreferences =  getActivity().getSharedPreferences(Constants.CHECKBOXES_PREFERENCES,  Context.MODE_PRIVATE);
		Editor editor =  nodesSharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.btnReserveNodes: 
				final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
				ft.replace(R.id.layoutToReplace_nitosScheduler, new ReserveResourcesFragment()); 
				//ft.addToBackStack(null);
				ft.commit();
	            
				Log.i("Available Nodes","reserve button");
				break;
			}
		}
		

	/* Each time a listview item is checked, this function is called to check which resources are available*/
	public void checkAvailableResources(Constants.HardwareType hwType){
	 	
		switch(hwType){
	 	
			case ORBIT:
				Log.i("Check Available Nodes","Orbit Nodes");
				HashMap<String, ResourcesData> orbitNodes = appState.getOrbitNodes();
				appState.clearOrbitAvailableNodes();
	 		
				for (Map.Entry<String, ResourcesData>entry:orbitNodes.entrySet()) {
					Log.i("Available Nodes", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
					ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++){
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
							break; 
					}
	 				
					//if the resource is available add it to the OrbitAvailableNodes 
					if(available) {
						String node_name = entry.getKey();
						String uuid = resourcesData.getUUID();
						appState.setOrbitAvailableNodes(node_name, uuid);
	 				}
				}

				break;	
			case GRID:
	 			   Log.i("Check Available Nodes","Grid Nodes");
	 			   HashMap<String, ResourcesData> gridNodes = appState.getGridNodes();
	 			   appState.clearGridAvailableNodes();
	 		
	 			   for (Map.Entry<String, ResourcesData>entry:gridNodes.entrySet()) {
					Log.i("Available Nodes", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
	 				ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++){
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
							break; 
	 				  }
	 				   
	 				  if(available) {
						String node_name = entry.getKey();
						String uuid = resourcesData.getUUID();
						appState.setGridAvailableNodes(node_name, uuid);
					}
				}

				break;	
			case USRP:
				Log.i("Check Available Nodes","USRP Nodes");
				HashMap<String, ResourcesData> usrpNodes = appState.getUsrpNodes();
				appState.clearUsrpAvailableNodes();
	 		
				for (Map.Entry<String,ResourcesData>entry:usrpNodes.entrySet()) {
					Log.i("Available Nodes", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
					ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++) {
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
							break; 
					}
	 				   
					if(available) {
						String node_name = entry.getKey();
						String uuid = resourcesData.getUUID();
						appState.setUsrpAvailableNodes(node_name, uuid);
					}
				}

				break;
			case DISKLESS:
				Log.i("Check Available Nodes","Diskless Nodes");
				HashMap<String, ResourcesData> disklessNodes = appState.getDisklessNodes();
				appState.clearDisklessAvailableNodes();
	 		
				for (Map.Entry<String, ResourcesData>entry:disklessNodes.entrySet()) {
					Log.i("Available Nodes", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
					ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++){
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
							break; 
					}
	 				   
					if(available) {
						String node_name = entry.getKey();
						String uuid = resourcesData.getUUID();
					}
				}

				break;
			case ICARUS:
				Log.i("Check Available Nodes","Icarus Nodes");
				HashMap<String, ResourcesData> icarusNodes = appState.getIcarusNodes();
				appState.clearIcarusAvailableNodes();
	 		
				for (Map.Entry<String, ResourcesData>entry:icarusNodes.entrySet()) {
					Log.i("Available Nodes", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
					ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++){
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
						break; 
					}
	 				   
					if(available) {
						String node_name = entry.getKey();
						String uuid = resourcesData.getUUID();
						appState.setIcarusAvailableNodes(node_name, uuid);
					}
				}

				break;
			case BASE_STATIONS:
				Log.i("Check Available Nodes","Base Stations");
				HashMap<String, ResourcesData> baseStationsNodes = appState.getBaseStations();
				appState.clearBaseStationsAvailable();
	 		
				for (Map.Entry<String, ResourcesData>entry:baseStationsNodes.entrySet()) {
					Log.i("Available Nodes", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
					ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++){
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
							break; 
					}
	 				   
					if(available) {
						String node_name = entry.getKey();
						String uuid = resourcesData.getUUID();
						appState.setBaseStationsAvailable(node_name, uuid);
					}
				}
				
				break;
	 		   case CHANNELS_802_11A:
				Log.i("Check Available Channels bg","Channels bgs");
				HashMap<String, ResourcesData> channels_802_11a = appState.getChannels802_11a();
				appState.clearChannels_802_11a_available();
				for (Map.Entry<String, ResourcesData>entry:channels_802_11a .entrySet()) {
					Log.i("Available Channels bg", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
					ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++){
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
							break; 
					}
	 				   
					if(available) {
						String channel_name = entry.getKey();
						String uuid = resourcesData.getUUID();
						appState.setChannels_802_11a_available(channel_name, uuid);
					}
				}
				break;
			case CHANNELS_802_11BG:
				Log.i("Check Available Channels","Channels bg");
				HashMap<String, ResourcesData> channels_802_11bg = appState.getChannels802_11bg();
				for (Map.Entry<String, ResourcesData>entry:channels_802_11bg .entrySet()) {
					Log.i("Available Channels bg", "" + entry.getKey());
	 				   
					ResourcesData resourcesData = entry.getValue();
					ArrayList<Reservation> reservations = resourcesData.getReservations();
	 				   
					boolean available = true;
	 				   
					for(int i = 0; i < reservations.size(); i++){
						Reservation reservation_i = reservations.get(i);
						available = intersectDateIntervals(reservation_i);
						if(!available)
							break; 
					}
	 				   
					if(available) {
						String channel_name = entry.getKey();
						String uuid = resourcesData.getUUID();
						appState.setChannels_802_11bg_available(channel_name, uuid);
					}
				}
				default:
					break;
			}
	 		
		}
	 	
	//Check if the given date is in range of a reservation	
	public boolean intersectDateIntervals(Reservation reservation){
		DateTime dateTimeUserFrom = appState.getDateTimeUserFrom();
		DateTime dateTimeUserUntil = appState.getDateTimeUserUntil();
		if(dateTimeUserUntil.isBefore(reservation.getDateTimeFrom()) || dateTimeUserUntil.isEqual(reservation.getDateTimeFrom())){
			Log.i("intersect intervals","false");
			return true;
		} else if(dateTimeUserFrom.isAfter(reservation.getDateTimeUntil()) || dateTimeUserFrom.isEqual(reservation.getDateTimeUntil())){
			Log.i("intersect intervals","false");
			return true;
		}
		
		Log.i("intersect intervals","true");
		return false;
	}
	 	
}
