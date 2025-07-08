package com.sevvalislekter.dto;



import lombok.AllArgsConstructor;


import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class DtoEmployee {

    private String firstName;
    private String lastName;
    private String title;
    private String linkedinUrl;
    private String randomCode;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
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
