package com.mycompany.myfirstapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class ContactsPage extends ListActivity implements OnClickListener, OnItemClickListener{

	private ImageButton btnAdd;
	private ImageButton btnBack;
	private Button btnSearch;
	private myAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts_page);

		DBmanager dbm = new DBmanager(this);
		btnAdd = (ImageButton) findViewById(R.id.btnAdd);
		btnBack = (ImageButton) findViewById(R.id.btnBack);
		btnSearch = (Button) findViewById(R.id.btnSearch);
		ListView myList = (ListView) findViewById(android.R.id.list);
		
		myList.setOnItemClickListener(this);
		btnAdd.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		btnSearch.setOnClickListener(this);

		List<Contacts> savedContacts = dbm.getAllContacts();
		
		adapter = new myAdapter(this,savedContacts);
		myList.setAdapter(adapter);
		
	}
	
	@Override
	public void onClick(View v) 
	{
		if (btnAdd.getId() == v.getId())
		{
			Intent intent = new Intent(getApplicationContext(), EditContacts.class);
			startActivity(intent);
		}
		if (btnBack.getId() == v.getId())
		{
			Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(intent);
		}
		if (btnSearch.getId() == v.getId())
		{
			Intent intent = new Intent(getApplicationContext(), Search.class);
			startActivity(intent);
		}
		
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