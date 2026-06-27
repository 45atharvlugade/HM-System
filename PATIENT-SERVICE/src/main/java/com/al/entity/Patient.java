package com.al.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="patient_master")
@Setter
@Getter
public class Patient implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID patientId;
	
	private String patientCode ;
	
	@Column(nullable = false,length = 100)
	private String firstName;
	
	@Column(length = 100)
	private String middleName;
	
	@Column(nullable = false,length = 100)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	private Integer age;
	
	private LocalDate dateOfBirth;
	
	@Column(nullable = false,length = 30,unique = true)
	private String mobileNumber;
	
	@Column(nullable = false,length = 40,unique = true)
	private String email;
	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;
	
	@Column(name = "profile_photo_url", length = 500)
	
	private String profilePhotoUrl;
	

	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	@Enumerated(EnumType.STRING)
	private AccountStatus accountStatus;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	// ----------------------------------
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "patient")
	@Setter
	@Getter
	 @JsonManagedReference
	private PatientAddress patientAddress;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "patient")
	@Setter
	@Getter
	private EmergencyContact emergencyContact;
	
	
	public static String generatePatientCode(long sequenceNumber) {
	    return "PAT-" +
	            LocalDate.now().getYear() +
	            "-" +
	            String.format("%06d", sequenceNumber);
	}
}