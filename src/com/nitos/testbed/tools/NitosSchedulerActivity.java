package com.nitos.testbed.tools;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;


public class NitosSchedulerActivity extends MainMenuActivity  {
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nitos_scheduler);
		
		
		
		ActionBar  actionBar = getSupportActionBar(); 
		/* ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#172741"));     
  		actionBar.setBackgroundDrawable(colorDrawable);*/
        
		actionBar.setDisplayHomeAsUpEnabled(true);
               
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
		ActionBar.Tab selectTab = actionBar.newTab().setText(getString(R.string.tabname_selection_menu));
  		ActionBar.Tab outdoorTestbedTab = actionBar.newTab().setText(getString(R.string.tabname_outdoor_testbed));
		ActionBar.Tab indoorTestbedTab = actionBar.newTab().setText(getString(R.string.tabname_indoor_testbed));
        
		Fragment selectFragment = new SchedulerChooserFragment();
		Fragment outdoorTestbedFragment = new OutdoorTestbedFragment();
		Fragment indoorTestbedFragment = new IndoorTestbedFragment();   
        
		selectTab.setTabListener(new MyTabsListener(selectFragment, getApplicationContext()));
		outdoorTestbedTab.setTabListener(new MyTabsListener(outdoorTestbedFragment, getApplicationContext()));
		indoorTestbedTab.setTabListener(new MyTabsListener(indoorTestbedFragment, getApplicationContext()));
 
        
		actionBar.addTab(selectTab);
		actionBar.addTab(outdoorTestbedTab);
 		actionBar.addTab(indoorTestbedTab);
	}
	 
 
	

	class MyTabsListener implements ActionBar.TabListener {
		public Fragment fragment;
		public Context context;
		
		public MyTabsListener(Fragment fragment, Context context) {
			this.fragment = fragment;
			this.context = context;
		}
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected(Tab arg0, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.replace(R.id.layoutToReplace_nitosScheduler, fragment);
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.remove(fragment);
		}		
	}
	 
}
