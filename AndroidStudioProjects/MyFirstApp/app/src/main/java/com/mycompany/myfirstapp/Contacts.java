package com.mycompany.myfirstapp;

public class Contacts 
{
    
    private int contactID;
    private String name;
    private String phone;
    private String email;
    private String address;
     
    public Contacts(){
         
    }
    public Contacts(int id, String _name, String _phone, String _email, String _address){
        this.contactID = id;
        this.name = _name;
        this.phone = _phone;
        this.email = _email;
        this.address = _address;
    }
     
    public Contacts(String _name, String _phone, String _email, String _address)
    {
    	 this.name = _name;
         this.phone = _phone;
         this.email = _email;
         this.address = _address;
    }
    public int getID(){
        return this.contactID;
    }
     
    public void setID(int id){
        this.contactID = id;
    }
     
    public String getName(){
        return this.name;
    }
     
    public void setName(String name){
        this.name = name;
    }
     
    public String getPhone(){
        return this.phone;
    }
     
    public void setPhone(String _phone){
        this.phone = _phone;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String _email){
        this.email = _email;
    }
    public String getAddress(){
        return this.address;
    }
    
    public void setAddress(String _address){
        this.address = _address;
    }
    
    @Override
	public String toString() 
    {
		return name;
	}
}