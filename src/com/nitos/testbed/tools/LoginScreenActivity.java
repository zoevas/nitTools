package com.nitos.testbed.tools;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;


public class LoginScreenActivity  extends Activity implements OnClickListener{

	
	private EditText  usernameEditText = null;
	private EditText  passwordEditText = null;
	private Button signInButton;
	private CheckBox keepMeSignedInCheckbox;
	private SharedPreferences loginPreferences;
	private SharedPreferences.Editor loginPrefsEditor;
	private boolean saveLogin;
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login_screen);

		loginPreferences = getSharedPreferences(Constants.LOGINPREFS, 0);
		loginPrefsEditor = loginPreferences.edit();
		
		usernameEditText = (EditText)findViewById(R.id.usernameEditText);
		passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        	keepMeSignedInCheckbox = (CheckBox)findViewById(R.id.keep_me_signed_in_checkbox);

		keepMeSignedInCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGINPREFS, 0);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putBoolean("isChecked", isChecked);
				Log.i("LoginActivity", "isChecked" +  " is " + isChecked);
				editor.commit();
			}
		});    
        
		signInButton = (Button)findViewById(R.id.signinButton);
		signInButton.setOnClickListener(this);
         
		saveLogin = loginPreferences.getBoolean("saveLogin", false);
		if (saveLogin == true) {
			usernameEditText.setText(loginPreferences.getString("username", ""));
			passwordEditText.setText(loginPreferences.getString("password", ""));
			keepMeSignedInCheckbox.setChecked(true);
		}
       
		if(keepMeSignedInCheckbox.isChecked()){
			Intent intent = new Intent();  
			intent.setClass(LoginScreenActivity.this, MainMenuActivity.class);  
			startActivity(intent);  
		}
	}
	



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.signinButton){
			Log.i("LoginActivity","signin button");
		
			String username =  usernameEditText.getText().toString();
			String password =  passwordEditText.getText().toString();

			boolean isChecked = loginPreferences.getBoolean("isChecked", false);
			Log.i("LoginActivity signin button", "isChecked" +  " is " + isChecked);
			if (isChecked) {  
				//Save preferences for next login
				loginPrefsEditor.putBoolean("saveLogin", true);
				loginPrefsEditor.putString("username", username);
				loginPrefsEditor.putString("password", password);
				loginPrefsEditor.commit();     
	        } else {
				loginPrefsEditor.clear().commit();
			}
		
			if(username.equals("ardadouk")){
				Intent intent = new Intent();  
				intent.setClass(LoginScreenActivity.this, MainMenuActivity.class);  
				startActivity(intent);  
			}
		}
	}
	

}
