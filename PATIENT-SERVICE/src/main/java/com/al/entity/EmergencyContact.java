package com.al.entity;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "patient_emergency_contact_master")
@Data
public class EmergencyContact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID emergencyContactId;
	
	private String contactName;
	
	private String relationship;
	
	private String emergencyMobileNumber;
	
	private String alternateNumber;
	
	@OneToOne(targetEntity = Patient.class)
	@JoinColumn(name = "patient_id",referencedColumnName = "patientId")
	@JsonBackReference
	private Patient patient;
}