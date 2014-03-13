package com.example.basicandroidcontrols;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private ProgressDialog pgLogin;
	EditText userName;
	EditText password;
	CheckBox remember_me;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		userName = (EditText)findViewById(R.id.editTextUsername);
		password = (EditText)findViewById(R.id.editTextPassword);
		remember_me = (CheckBox) findViewById(R.id.remember_me_checkBox);


		String remember_username = getFromPreference("remember_username");
		String remember_password = getFromPreference("remember_password");

		userName.setText(remember_username);
		password.setText(remember_password);
	}

	public void signIn(View v) 
	{
		try 
		{
			if(userName.getText().toString().length()<1 || password.getText().toString().length()<1)
			{
				Toast.makeText(this, "Please provide all information.", Toast.LENGTH_LONG).show();
			}
			else 
			{
				pgLogin = new ProgressDialog(LoginActivity.this);
				pgLogin.setMessage("Please wait while progress login...");
				pgLogin.setIndeterminate(true);
				pgLogin.setCancelable(true);
				pgLogin.setCanceledOnTouchOutside(false);

				pgLogin.show();

				if (remember_me.isChecked()) 
				{
					saveInPreference("remember_username", userName.getText().toString());
					saveInPreference("remember_password", password.getText().toString());
				}
				else
				{
					saveInPreference("remember_username", "");
					saveInPreference("remember_password", "");
				}

				if(userName.getText().toString().equalsIgnoreCase("admin") && password.getText().toString().equalsIgnoreCase("admin"))
				{
					if (pgLogin.isShowing()) 
					{
						pgLogin.cancel();
						pgLogin.dismiss();
					}
					Intent it = new Intent(LoginActivity.this, BasicControlActivity.class);
					it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					finish();
					startActivity(it);
				}
				else
				{
					if (pgLogin.isShowing()) 
					{
						pgLogin.cancel();
						pgLogin.dismiss();
					}
					Toast.makeText( getApplicationContext(),"Login failed. Please check your credentials and try again.",Toast.LENGTH_LONG).show();
				}
			}
		} 
		catch (Throwable e) 
		{
			Log.d("Log",e+"");
		}
	}



	public void quit(View v) {
		finish();
	}


	public void saveInPreference(String name, String content) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(name, content);
		editor.commit();
	}

	public String getFromPreference(String variable_name) {
		String preference_return;
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		preference_return = preferences.getString(variable_name, "");

		return preference_return;
	}
}
