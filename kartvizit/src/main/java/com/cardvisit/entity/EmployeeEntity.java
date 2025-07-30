package com.cardvisit.entity;


import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class EmployeeEntity {
	
	public enum Status{
		QR_Basıldı,
		Nfc_Yazıldı,
		Kart_Hazır
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate startDate;
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
    @Column(name = "qr_code_url")
    private String qrCodeUrl;
    @Column(name = "exit_date")
    private LocalDate exitDate;
    @Column(name = "is_active",nullable = false)
    private boolean qrActive=true;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
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
    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
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
    public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}


   
}
