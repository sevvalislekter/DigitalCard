package com.cardvisit.dto;
import java.time.LocalDate;

import com.cardvisit.entity.EmployeeEntity.Status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String title;
    private String linkedinUrl;
    private String randomCode;
    private String email;
    private String phoneNumber;
	private String photoUrl;
	private String qrCodeUrl;
    private LocalDate exitDate;
    private boolean qrActive=true;
    private LocalDate startDate;
    private  Status status ;

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
	public String getPhotoUrl() {
	    	return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
	    	this.photoUrl=photoUrl;
	}
	public boolean getQrActive() {
	    return qrActive;
	}

	public void setQrActive(boolean qrActive) {
	    this.qrActive = qrActive;
	}

	public LocalDate getExitDate() {
	    return exitDate;
	}

	public void setExitDate(LocalDate exitDate) {
	    this.exitDate = exitDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

}
