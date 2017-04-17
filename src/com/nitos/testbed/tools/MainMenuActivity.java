package com.nitos.testbed.tools;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nitos.testbed.tools.R;


public class MainMenuActivity extends ActionBarActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		
		ActionBar  actionBar = getSupportActionBar(); 
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#172741"));     
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setDisplayShowTitleEnabled(false);
        
        
        ListView menuList = (ListView) findViewById(R.id.ListView_Menu);
		 
		String[] items = { getResources().getString(R.string.menu_item_nitos_scheduler),
	               getResources().getString(R.string.menu_item_connectivity_tool),
	               getResources().getString(R.string.menu_item_node_distance_tool),
	               getResources().getString(R.string.menu_item_openflow_settings), 
	               getResources().getString(R.string.menu_item_measurement_map_depiction) ,
	               getResources().getString(R.string.menu_item_testbed_status) ,
	               getResources().getString(R.string.menu_item_cm_framework) ,
	               getResources().getString(R.string.menu_item_usb_sensors_toolkit)
	             };
		 
		ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.menu_item, items);
	    menuList.setAdapter(adapt);
		
	    menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

                // Note: if the list was built "by hand" the id could be used.
                // As-is, though, each item has the same id
            	           	
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();

                if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_nitos_scheduler))) {
                    // Launch the Game Activity
                    startActivity(new Intent(MainMenuActivity.this, NitosSchedulerActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_connectivity_tool))) {
                    // Launch the Help Activity
                    //startActivity(new Intent(QuizMenuActivity.this, QuizHelpActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_node_distance_tool))) {
                    // Launch the Settings Activity
                    //startActivity(new Intent(QuizMenuActivity.this, QuizSettingsActivity.class));
                } else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_openflow_settings))) {
                    // Launch the Scores Activity
                    //startActivity(new Intent(QuizMenuActivity.this, QuizScoresActivity.class));
                }
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_measurement_map_depiction))) {
                // Launch the Scores Activity
                //startActivity(new Intent(QuizMenuActivity.this, QuizScoresActivity.class));
                }
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_testbed_status))) {
                  // Launch the Scores Activity
                 //startActivity(new Intent(QuizMenuActivity.this, QuizScoresActivity.class));
                }
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_cm_framework))) {
                    // Launch the Scores Activity
                   //startActivity(new Intent(QuizMenuActivity.this, QuizScoresActivity.class));
                }
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_usb_sensors_toolkit))) {
                    // Launch the Scores Activity
                   //startActivity(new Intent(QuizMenuActivity.this, QuizScoresActivity.class));
                }

            }
            
          
        });       
        
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
	    if( id== R.id.home){
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    }
	    else if(id == R.id.action_user_reservations){
	    	Intent intent = new Intent(this, MyReservationsActivity.class);
	    	startActivity(intent);
	    }
	    else if(id == R.id.logout) {          
           SharedPreferences loginPreferences = getSharedPreferences(Constants.LOGINPREFS, 0);
           Editor loginPrefsEditor = loginPreferences.edit();
		   loginPrefsEditor.putBoolean("saveLogin", false).commit();
           
		   Intent intent = new Intent();  
		   intent.setClass(MainMenuActivity.this, LoginScreenActivity.class);  
		   startActivity(intent);  
	    }
		return super.onOptionsItemSelected(item);
	}

	

}
