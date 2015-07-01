package com.mycompany.myfirstapp;

class User
{ 
    private int userID;
	private String name;
    private String password;
     
    public User(){
         
    }
    public User(int id, String _name, String _password){
        this.userID = id;
        this.name = _name;
        this.password = _password;
    }
     
    public User(String _name, String _password)
    {
    	this.name = _name;
        this.password = _password;
    }

	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}