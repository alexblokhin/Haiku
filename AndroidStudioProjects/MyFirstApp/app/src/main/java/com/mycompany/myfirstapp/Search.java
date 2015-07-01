package com.mycompany.myfirstapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Search extends ListActivity implements OnClickListener, OnItemClickListener{

	private ImageButton btnBack;
	private EditText txtSearch;
	private Button btnFind;
	private ListView myList;
	private myAdapter adapter;
	private ArrayList<Contacts> foundContacts;
	private List<Contacts> savedContacts;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		DBmanager dbm = new DBmanager(this);
		btnBack = (ImageButton) findViewById(R.id.btnBack);
		btnFind = (Button) findViewById(R.id.btnFind);
		myList = (ListView)findViewById(android.R.id.list);
		txtSearch = (EditText) findViewById(R.id.txtSearch);

		myList.setOnItemClickListener(this);
		btnBack.setOnClickListener(this);
		btnFind.setOnClickListener(this);

		savedContacts = dbm.getAllContacts();
		foundContacts = new ArrayList<>();
		
		adapter = new myAdapter(this,foundContacts);
		
	}
	
	@Override
	public void onClick(View v) 
	{
		if (btnFind.getId() == v.getId())
		{
			foundContacts.clear(); //resets for every search
			myList.setAdapter(adapter);
			if(txtSearch.getText().toString().trim().length() != 0)
			{
				String searchName = txtSearch.getText().toString();
				boolean isFound = findContact(searchName);
				if(isFound)
				{
					myList.setAdapter(adapter);
				}
				else
					Toast.makeText(getApplicationContext(), "Name does not match our records",
							   Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Enter all information",
						   Toast.LENGTH_LONG).show();
			}
			
		}
	
		if (btnBack.getId() == v.getId())
		{
			Intent intent = new Intent(getApplicationContext(), ContactsPage.class);
			startActivity(intent);
		}
		
	}
	private boolean findContact(String searchName)
	{
		boolean isFound = false;
		for(Contacts c : savedContacts)
		{
			if(c.getName().equalsIgnoreCase(searchName))
			{
				foundContacts.add(c);
				isFound = true;
			}
		}
		return isFound;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Contacts item = adapter.getItem(position);
		int objectID = item.getID();
		Intent intent = new Intent(getApplicationContext(), EditContacts.class);
		intent.putExtra("objectID",objectID);
		startActivity(intent);
		
	}



}