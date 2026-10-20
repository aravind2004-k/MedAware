package com.medaware.model;

public class User {
	private int userId;
	private String fullName;
	private String email;
	private String password;
	//Default constructor
	public User()
	{
		
	}
	//Parameterized constructor
	public User(String fullName,String email,String password)
	{
		this.fullName = fullName;
		this.email =email;
		this.password= password;
	}
	//Getter and Setter Methods
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
	
	
