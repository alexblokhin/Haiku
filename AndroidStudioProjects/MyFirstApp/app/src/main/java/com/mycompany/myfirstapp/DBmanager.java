package com.mycompany.myfirstapp;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
 
class DBmanager extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String TABLE_USERS = "users";
    //private static final String TABLE_FAVORITES = "favorites";

    // Users table column names
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
 
    // Contacts table columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "address";
    
    //Favorites table column names
   /* private static final String KEY_FAV_ID = "id";
    private static final String KEY_FAV_NAME = "name";
    private static final String KEY_FAV_PHONE = "phone";
    private static final String KEY_FAV_EMAIL = "email";
    private static final String KEY_FAV_ADDRESS = "address";*/
 
    public DBmanager(Context context) 
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_ADDRESS +
                " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
        
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);
        
        /*String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + KEY_FAV_ID + " INTEGER PRIMARY KEY," + KEY_FAV_NAME + " TEXT,"
                + KEY_FAV_PHONE + " TEXT," + KEY_FAV_EMAIL + " TEXT," + KEY_FAV_ADDRESS +
                " TEXT)";
        db.execSQL(CREATE_FAVORITES_TABLE);*/
        
    }
    
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);


        // Create tables again
        onCreate(db);
    }
    
    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getName());
        values.put(KEY_PASSWORD, user.getPassword());  
 
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> userList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUserID(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        return userList;
    }
    public void addContact(Contacts contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone()); 
        values.put(KEY_EMAIL, contact.getEmail()); 
        values.put(KEY_ADDRESS, contact.getAddress()); 
 
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }
 
    public Contacts getContact(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_ADDRESS}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contacts contact = new Contacts(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4));
        return contact;
    }
     
    public List<Contacts> getAllContacts() {
        List<Contacts> contactList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        if (cursor.moveToFirst()) {
            do {
                Contacts contact = new Contacts();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setAddress(cursor.getString(4));
                
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
 
    public int updateContact(Contacts contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        values.put(KEY_EMAIL, contact.getEmail());
        values.put(KEY_ADDRESS, contact.getAddress());
        
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }
 
    public void deleteContact(Contacts contact) 
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

}