package com.al.requestdto;

import java.io.Serializable;

import com.al.entity.BloodGroup;
import com.al.entity.Gender;
import com.al.entity.Status;

public class PatientSearchAndFilterRequestDTO implements Serializable {

	private Gender gender;
	
	private Integer age;
	
	private BloodGroup bloodGroup;
	
	private String city;
	
	private String state;
	
	private Status status;
}