package com.cardvisit.dto;


import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeIUDTO {
	
	
    private  String firstName;
    private String lastName;
    private String email;
    private  String title;
    private  String phoneNumber;
    private  String linkedinUrl;
    private  String randomCode;
	private String photoUrl;
	private String qrCodeUrl;
    private LocalDate exitDate;
    private Boolean qrActive = true;  
    private MultipartFile photo;
	public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
	public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
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
	public String getRandomCode() {
		return randomCode;
	}
	public void setRandomCode(String randomCode) {
		this.randomCode=randomCode;
	}
	 public String getPhotoUrl() {
	    	return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
	    	this.photoUrl=photoUrl;
	}
	public Boolean getQrActive() {
	    return qrActive;
	}

	public void setQrActive(Boolean qrActive) {
	    this.qrActive = qrActive;
	}

	public LocalDate getExitDate() {
	    return exitDate;
	}

	public void setExitDate(LocalDate exitDate) {
	    this.exitDate = exitDate;
	}

}

