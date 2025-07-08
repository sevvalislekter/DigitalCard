package com.sevvalislekter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "title")
    private String title;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "linkedin_url")
    private String linkedinUrl;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "random_code", unique = true,length = 8)
    private String randomCode;
 
    
    public Integer getId() {
    	return id;
    }
    public void setId(Integer id) {
    	this.id=id;
    }
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
    public String getPhotoUrl() {
    	return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
    	this.photoUrl=photoUrl;
    }
    public String getRandomCode() {
    	return randomCode;
    }
    public void setRandomCode(String randomCode) {
    	this.randomCode=randomCode;
    }

   
}
