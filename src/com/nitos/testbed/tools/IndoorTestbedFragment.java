package com.nitos.testbed.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IndoorTestbedFragment extends Fragment {
	
	View rootView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		rootView =  inflater.inflate(R.layout.indoor_testbed_fragment, container, false);
		return rootView;
	}
	 
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("IndoorTestbedFragment", "onPause");
	}
	 
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("IndoorTestbedFragment", "onResume");
	}
}
