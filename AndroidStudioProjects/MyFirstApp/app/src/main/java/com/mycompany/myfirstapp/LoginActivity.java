package com.mycompany.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends ActionBarActivity implements OnClickListener{

	private DBmanager dbm;
	private Button btnLogin;
	private EditText txtUsername;
	private EditText txtPass;
	private TextView newAccount;
	private Animation shake;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		dbm = new DBmanager(this);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		txtUsername = (EditText) findViewById(R.id.txtUsername);
		txtPass = (EditText) findViewById(R.id.txtPass);
		newAccount = (TextView) findViewById(R.id.createAccount);

		newAccount.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		if (btnLogin.getId() == v.getId())
		{
			
		    String name = txtUsername.getText().toString();
			String pass = txtPass.getText().toString();
			if(name.trim().length() != 0 && pass.trim().length() != 0)
			{	
				boolean valid = findUser(name,pass);
				if(valid)
				{
					Intent intent = new Intent(getApplicationContext(), ContactsPage.class);
					startActivity(intent);
				}
				else
				{
					btnLogin.startAnimation(shake);
					Toast.makeText(getApplicationContext(), "Did not match our records",
							Toast.LENGTH_LONG).show();
				}
			}
			else
			{
				btnLogin.startAnimation(shake);
				Toast.makeText(getApplicationContext(), "Enter all information",
						Toast.LENGTH_LONG).show();	
			}
						
		}
		
		if (newAccount.getId() == v.getId())
		{
			Intent intent = new Intent(getApplicationContext(), NewUser.class);
			startActivity(intent);
		}

	}
	private boolean findUser(String name, String pass)
	{
		boolean validUser = false;

		ArrayList<User> allUsers = dbm.getAllUsers();
		for(User u : allUsers)
		{
			String tmpName = u.getName();
			String tmpPass = u.getPassword();
			if(name.equals(tmpName) && pass.equals(tmpPass))
			{
				validUser = true;
			}
		}
		return validUser;
	}

}