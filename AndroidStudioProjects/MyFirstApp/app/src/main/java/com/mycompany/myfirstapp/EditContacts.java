package com.mycompany.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class EditContacts extends ActionBarActivity implements OnClickListener {

    private EditText txtName;
    private EditText txtPhone;
    private EditText txtEmail;
    private EditText txtAddress;
    private ImageButton btnSave;
    private ImageButton btnBack;
    private Button btnDelete;
    private boolean newContact = true;

    private DBmanager dbm;
    private Contacts tempContact;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacts);

        dbm = new DBmanager(this);
        txtName = (EditText) findViewById(R.id.txtName);
        txtPhone = (EditText) findViewById(R.id.txtPhoneNum);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        TextView displayName = (TextView) findViewById(R.id.displayName);


        btnSave = (ImageButton) findViewById(R.id.btnSave);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnDelete = (Button) findViewById(R.id.btnDone);

        btnDelete.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int objectID = getIntent().getExtras().getInt("objectID");
            tempContact = dbm.getContact(objectID);
            displayName.setText(tempContact.getName());
            txtName.setText(tempContact.getName());
            txtPhone.setText(tempContact.getPhone());
            txtEmail.setText(tempContact.getEmail());
            txtAddress.setText(tempContact.getAddress());
            newContact = false;
        }

    }

    @Override
    public void onClick(View v) {
        if (btnSave.getId() == v.getId()) {
            if (newContact) {
                String name = txtName.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtEmail.getText().toString();
                String address = txtAddress.getText().toString();
                Contacts contact = new Contacts(name, phone, email, address);
                dbm.addContact(contact);
            } else {
                tempContact.setName(txtName.getText().toString());
                tempContact.setPhone(txtPhone.getText().toString());
                tempContact.setEmail(txtEmail.getText().toString());
                tempContact.setAddress(txtAddress.getText().toString());

                dbm.updateContact(tempContact);
            }
            Intent intent = new Intent(getApplicationContext(), ContactsPage.class);
            startActivity(intent);
        }
        if (btnBack.getId() == v.getId()) {
            Intent intent = new Intent(getApplicationContext(), ContactsPage.class);
            startActivity(intent);
        }
        if (btnDelete.getId() == v.getId()) {
            if (tempContact != null) {
                dbm.deleteContact(tempContact);
            } else {
                Toast.makeText(getApplicationContext(), "No contact selected.",
                        Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(getApplicationContext(), ContactsPage.class);
            startActivity(intent);
        }

    }


}