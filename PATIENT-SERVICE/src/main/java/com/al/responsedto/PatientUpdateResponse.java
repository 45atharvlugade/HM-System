package com.al.responsedto;

import java.io.Serializable;
import java.util.UUID;

import com.al.entity.BloodGroup;
import com.al.entity.Gender;
import com.al.entity.Status;

import lombok.Data;


@Data
public class PatientUpdateResponse implements Serializable{

	    private UUID patientId;
	
	    private String firstName;

	    private String middleName;

	    private String lastName;
	    
	    private Integer age;

	    private Gender gender;

	    private BloodGroup bloodGroup;

	    private String email;

	    private String mobileNumber;

	    private Status status;
}