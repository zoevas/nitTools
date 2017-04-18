package com.nitos.testbed.tools;

import java.util.Set;
import java.util.TreeMap;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

/*This fragment is nested to the AvailableResourcesFragment. It displays the checkboxes of all resources and
  keeps their state.*/
public class CheckResourcesFragment extends Fragment {
	
	GlobalData appState;
	
	SharedPreferences nodesSharedPreferences;
	 
	LinearLayout checkNodesView;
	Constants.HardwareType resource_type;
	 
	public TreeMap<String, String> currentFragmentNodes;
	 
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Log.i("CheckNodesFragment","onCreate");
		//This initialization may be needed in the onCreateView
		appState = ((GlobalData)getActivity().getApplicationContext());
		nodesSharedPreferences =  getActivity().getSharedPreferences(Constants.CHECKBOXES_PREFERENCES,    Context.MODE_PRIVATE);
	}
	 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i("CheckNodesFragment","onCreateView");
		checkNodesView =  (LinearLayout)inflater.inflate(R.layout.check_resources_fragment, container, false);

		return checkNodesView;
	}
	 
	 
	//We get the resource type via the bundle
	public void onResume(){
		super.onResume();
		Log.i("CheckNodesFRagment","onResume");
	
		Bundle b = getArguments();
	 		
		resource_type = Constants.HardwareType.valueOf(b.getString("resource_type"));
	 	  
		setCheckBoxes(resource_type);
		  
	}	 
	 
	/* It displays the checkboxes of the selected listview item keeping their state.*/
	public void setCheckBoxes(Constants.HardwareType resource_type){
		switch (resource_type)	{
			case ORBIT:{
				appState.clearOrbitCheckBoxes();
				currentFragmentNodes = appState.getOrbitAvailableNodes();
				Set<String> keys = currentFragmentNodes.keySet();
		    
				LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
				int checkBoxIndex = 0;
			
				for(String key: keys){
					String node_name = key;
		    	    	
					appState.setOrbitCheckBoxes(new CheckBox(checkNodesView.getContext()));
					appState.setOrbitCheckBoxesText(checkBoxIndex, node_name);
			        	
					//Restore the CheckBoxes state
					if (nodesSharedPreferences.contains(node_name)) {
				        	boolean checkBoxValue = nodesSharedPreferences.getBoolean(node_name, false);
				        
							if (checkBoxValue) {
								appState.getOrbitCheckBoxes().get(checkBoxIndex).setChecked(true);
							} else {
								appState.getOrbitCheckBoxes().get(checkBoxIndex).setChecked(false);
							}
					}
			        
					ll.addView(appState.getOrbitCheckBoxes().get(checkBoxIndex));
					checkBoxIndex++;
				}
		    	    
				break;
			}
			case GRID:{
				appState.clearGridCheckBoxes();
				currentFragmentNodes = appState.getGridAvailableNodes();
				Set<String> keys = currentFragmentNodes.keySet();
		    
				LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
				int checkBoxIndex = 0;
				
				for(String key: keys){
					String node_name = key;
		    	    	
					appState.setGridCheckBoxes(new CheckBox(checkNodesView.getContext()));
					appState.setGridCheckBoxesText(checkBoxIndex, node_name);
			        	
					//Restore the CheckBoxes state
					if (nodesSharedPreferences.contains(node_name)) {
						boolean checkBoxValue = nodesSharedPreferences.getBoolean(node_name, false);
				        
						if (checkBoxValue) {
							appState.getGridCheckBoxes().get(checkBoxIndex).setChecked(true);
						} else {
							appState.getGridCheckBoxes().get(checkBoxIndex).setChecked(false);
						}
					}
			        
					ll.addView(appState.getGridCheckBoxes().get(checkBoxIndex));
					checkBoxIndex++;
				}
		    	    
				break;
			}
		     case USRP:{
		    	    appState.clearUsrpCheckBoxes();
		    	    currentFragmentNodes = appState.getUsrpAvailableNodes();
		    	    Set<String> keys = currentFragmentNodes.keySet();
		    
		    	    LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
		    	    int checkBoxIndex = 0;
		    	    for(String key: keys){
		    	    	String node_name = key;
		    	    	
		    	    	appState.setUsrpCheckBoxes(new CheckBox(checkNodesView.getContext()));
			        	appState.setUsrpCheckBoxesText(checkBoxIndex, node_name);
			        	
			        	//Restore the CheckBoxes state
			        	if (nodesSharedPreferences.contains(node_name)) {
				        	boolean checkBoxValue = nodesSharedPreferences.getBoolean(node_name, false);
				        
				        	if (checkBoxValue) {
				        		appState.getUsrpCheckBoxes().get(checkBoxIndex).setChecked(true);
				        	} else {
				        		appState.getUsrpCheckBoxes().get(checkBoxIndex).setChecked(false);
				        	}
				        }
			        
				       ll.addView(appState.getUsrpCheckBoxes().get(checkBoxIndex));
				       checkBoxIndex++;
		    	    }
		    	    
		    	 break;
		     }
		     case  DISKLESS:{
		    	 appState.clearDisklessCheckBoxes();
		    	    currentFragmentNodes = appState.getDisklessAvailableNodes();
		    	    Set<String> keys = currentFragmentNodes.keySet();
		    
		    	    LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
		    	    int checkBoxIndex = 0;
		    	    for(String key: keys){
		    	    	String node_name = key;
		    	    	
		    	    	appState.setDisklessCheckBoxes(new CheckBox(checkNodesView.getContext()));
			        	appState.setDisklessCheckBoxesText(checkBoxIndex, node_name);
			        	
			        	//Restore the CheckBoxes state
			        	if (nodesSharedPreferences.contains(node_name)) {
				        	boolean checkBoxValue = nodesSharedPreferences.getBoolean(node_name, false);
				        
				        	if (checkBoxValue) {
				        		appState.getDisklessCheckBoxes().get(checkBoxIndex).setChecked(true);
				        	} else {
				        		appState.getDisklessCheckBoxes().get(checkBoxIndex).setChecked(false);
				        	}
				        }
			        
				       ll.addView(appState.getDisklessCheckBoxes().get(checkBoxIndex));
				       checkBoxIndex++;
		    	    }
		    	    
		    	 break;
		     }
		     case ICARUS:{
		    	 appState.clearIcarusCheckBoxes();
		    	    currentFragmentNodes = appState.getIcarusAvailableNodes();
		    	    Set<String> keys = currentFragmentNodes.keySet();
		    
		    	    LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
		    	    int checkBoxIndex = 0;
		    	    for(String key: keys){
		    	    	String node_name = key;
		    	    	
		    	    	appState.setIcarusCheckBoxes(new CheckBox(checkNodesView.getContext()));
			        	appState.setIcarusCheckBoxesText(checkBoxIndex, node_name);
			        	
			        	//Restore the CheckBoxes state
			        	if (nodesSharedPreferences.contains(node_name)) {
				        	boolean checkBoxValue = nodesSharedPreferences.getBoolean(node_name, false);
				        
				        	if (checkBoxValue) {
				        		appState.getIcarusCheckBoxes().get(checkBoxIndex).setChecked(true);
				        	} else {
				        		appState.getIcarusCheckBoxes().get(checkBoxIndex).setChecked(false);
				        	}
				        }
			        
				       ll.addView(appState.getIcarusCheckBoxes().get(checkBoxIndex));
				       checkBoxIndex++;
		    	    }
		    	    
		    	 break;
		     }
		     case BASE_STATIONS:{
		 	        appState.clearBaseStationCheckBoxes();
		    	    currentFragmentNodes = appState.getBaseStationsAvailable();
		    	    Set<String> keys = currentFragmentNodes.keySet();
		    
		    	    LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
		    	    int checkBoxIndex = 0;
		    	    for(String key: keys){
		    	    	String node_name = key;
		    	    	
		    	    	appState.setBaseStationCheckBoxes(new CheckBox(checkNodesView.getContext()));
			        	appState.setBaseStationCheckBoxesText(checkBoxIndex, node_name);
			        	
			        	//Restore the CheckBoxes state
			        	if (nodesSharedPreferences.contains(node_name)) {
				        	boolean checkBoxValue = nodesSharedPreferences.getBoolean(node_name, false);
				        
				        	if (checkBoxValue) {
				        		appState.getBaseStationCheckBoxes().get(checkBoxIndex).setChecked(true);
				        	} else {
				        		appState.getBaseStationCheckBoxes().get(checkBoxIndex).setChecked(false);
				        	}
				        }
			        
				       ll.addView(appState.getBaseStationCheckBoxes().get(checkBoxIndex));
				       checkBoxIndex++;
		    	    }
		    	    
		    	    break;
		     }
		     case CHANNELS_802_11A:{
		 	        appState.clearChannels_802_11a_checkBoxes();
		    	    currentFragmentNodes = appState.getChannels_802_11a_available();
		    	    Set<String> keys = currentFragmentNodes.keySet();
		    
		    	    LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
		    	    int checkBoxIndex = 0;
		    	    for(String key: keys){
		    	    	String channel_name = key;
		    	    	
		    	    	appState.setChannels_802_11a_checkBoxes(new CheckBox(checkNodesView.getContext()));
			        	appState.setChannels_802_11a_checkBoxesText(checkBoxIndex, channel_name);
			        	
			        	//Restore the CheckBoxes state
			        	if (nodesSharedPreferences.contains(channel_name)) {
				        	boolean checkBoxValue = nodesSharedPreferences.getBoolean(channel_name, false);
				        
				        	if (checkBoxValue) {
				        		appState.getChannels_802_11a_checkBoxes().get(checkBoxIndex).setChecked(true);
				        	} else {
				        		appState.getChannels_802_11a_checkBoxes().get(checkBoxIndex).setChecked(false);
				        	}
				        }
			        
				       ll.addView(appState.getChannels_802_11a_checkBoxes().get(checkBoxIndex));
				       checkBoxIndex++;
		    	    }
		    	    
		    	    break;
		     }
		     case CHANNELS_802_11BG:{
		 	        appState.clearChannels_802_11bg_checkBoxes();
		    	    currentFragmentNodes = appState.getChannels_802_11bg_available();
		    	    Set<String> keys = currentFragmentNodes.keySet();
		    
		    	    LinearLayout ll = (LinearLayout)checkNodesView.findViewById(R.id.child_scrollView);
		    	    int checkBoxIndex = 0;
		    	    for(String key: keys){
		    	    	String channel_name = key;
		    	    	
		    	    	appState.setChannels_802_11bg_checkBoxes(new CheckBox(checkNodesView.getContext()));
			        	appState.setChannels_802_11bg_checkBoxesText(checkBoxIndex, channel_name);
			        	
			        	//Restore the CheckBoxes state
			        	if (nodesSharedPreferences.contains(channel_name)) {
				        	boolean checkBoxValue = nodesSharedPreferences.getBoolean(channel_name, false);
				        
				        	if (checkBoxValue) {
				        		appState.getChannels_802_11bg_checkBoxes().get(checkBoxIndex).setChecked(true);
				        	} else {
				        		appState.getChannels_802_11bg_checkBoxes().get(checkBoxIndex).setChecked(false);
				        	}
				        }
			        
				       ll.addView(appState.getChannels_802_11bg_checkBoxes().get(checkBoxIndex));
				       checkBoxIndex++;
		    	    }
		    	    
		    	    break;
		     }
		     default:
		    	 break;
		 	
		 }
		 
	 }
	
	
	 
	 public void onPause(){
		 
		 super.onPause();
		 Log.i("CheckNodesFRagment","onPause " + resource_type.toString());
		 Editor editor =  nodesSharedPreferences.edit();
		 Set<String> keys = currentFragmentNodes.keySet();
		 
		 //In this method we store the state of checkboxes
		 switch (resource_type)	{

		 	case ORBIT: {
		 		 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getOrbitCheckBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
		 	}
			case GRID:{
				 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getGridCheckBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
			}
			case USRP:{
				 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getUsrpCheckBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
			}
			case  DISKLESS:{
				 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getDisklessCheckBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
			}
			case ICARUS:{
				 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getIcarusCheckBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
			}
			case BASE_STATIONS:{
				 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getBaseStationCheckBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
			}
			case CHANNELS_802_11A:{
				 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getChannels_802_11a_checkBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
			}
			case CHANNELS_802_11BG:{
				 int checkBoxIndex = 0;
		 		 for(String key: keys) {
		 			editor.putBoolean(key, appState.getChannels_802_11bg_checkBoxes().get(checkBoxIndex).isChecked());
		 			checkBoxIndex++;
		 		 }
		 	     editor.commit();
			     break;
			}
			default:
			   	 break;
		 }
		
	 }
	 
  public void onStart(){
	     super.onStart();
	     Log.i("CheckNodesFragment", "onSTart");
  }
	 
  public void onAttach(Activity mActivity){
		 super.onAttach(mActivity);
		 Log.i("CheckNodesFRagment","onAttach");
	 }
  public void onActivityCreated(Bundle mArguments){
		 super.onActivityCreated(mArguments);
		 Log.i("CheckNodesFRagment","onActivityCreated");
	 }
	 
  
  public void onStop(){
	     super.onStop();
	     Log.i("CheckNodesFragment", "onStop");
  }	 
 
	 
  public void onDestroy(){
		 super.onDestroy();	 
	     Log.i("onDestroy", "onDestroy");
		
  } 
	 
  public void onDestroyView(){
		 super.onDestroyView();	 
		 Log.i("onDestroyView", "onDestroyView");
} 

   public void onDetach(){
		 super.onDetach();
		 Log.i("onDetach", "onDetach");
   }
}
