package com.mycompany.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class NewUser extends ActionBarActivity implements OnClickListener{

	private EditText txtUsername;
	private EditText txtPassword;
	private Button btnDone;
	private ImageButton btnBack;

	private DBmanager dbm;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_user);
		
		dbm = new DBmanager(this);
		txtUsername = (EditText) findViewById(R.id.txtName);
		txtPassword = (EditText) findViewById(R.id.txtPass);
		btnBack = (ImageButton) findViewById(R.id.btnBack);
		btnDone = (Button) findViewById(R.id.btnDone);
		btnDone.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) 
	{
		if (btnDone.getId() == v.getId())
		{
			String username = txtUsername.getText().toString();
			String pass = txtPassword.getText().toString();
			if(username.trim().length() != 0 && pass.trim().length() != 0)
			{	
				User newUser = new User(username,pass);
				dbm.addUser(newUser);
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Enter all information",
						Toast.LENGTH_LONG).show();	
			}
		}
		if (btnBack.getId() == v.getId())
		{
			Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
		}
		
	}
	

}