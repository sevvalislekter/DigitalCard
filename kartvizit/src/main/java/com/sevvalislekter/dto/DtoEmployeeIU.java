package com.sevvalislekter.dto;


import lombok.AllArgsConstructor;



@AllArgsConstructor
public class DtoEmployeeIU {
	
	
    private  String firstName;
    private String lastName;
    private String email;
    private  String title;
    private  String phoneNumber;
    private  String linkedinUrl;
    private  String randomCode;
	
	
    public String getFirstName() {
    	return firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName=firstName;
    }
    public String getLastName() {
    	return lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName=lastName;
    	
    }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	public String getLinkedinUrl() {
		return linkedinUrl;
	}
	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl=linkedinUrl;
		
	}
	public String getRandomCode() {
		return randomCode;
	}
	public void setRandomCode(String randomCode) {
		this.randomCode=randomCode;
	}
}

