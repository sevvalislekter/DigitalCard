package com.sevvalislekter.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String email;
    private String title;
    private String phoneNumber;
    private String linkedinUrl;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "random_code", unique = true)
    private String randomCode;


   
}
