package com.al.entity;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="patient_address_master")
@Data
public class PatientAddress implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID addressId;
	
	@Column(nullable = false,length = 100)
	private String addressLine1;
	
	private String addressLine2;
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false,length = 100)
	private String state;
	
	@Column(nullable = false,length = 100)
	private String country;
	
	@Column(nullable = false,length = 100)
	private String pincode;
	
	@OneToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patient_id",referencedColumnName = "patientId")
	@JsonBackReference
	private Patient patient;
}