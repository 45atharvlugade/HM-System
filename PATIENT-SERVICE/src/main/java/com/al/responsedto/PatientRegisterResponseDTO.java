package com.al.responsedto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.al.entity.BloodGroup;
import com.al.entity.Gender;
import com.al.entity.Status;

import lombok.Data;

@Data
public class PatientRegisterResponseDTO implements Serializable {

    private UUID patientId;

    private String patientCode;

    private String firstName;

    private String middleName;

    private String lastName;

    private Gender gender;

    private BloodGroup bloodGroup;

    private String email;

    private String mobileNumber;
    
    private Integer age;

    private Status status;

    private LocalDateTime createdAt;


    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String pincode;

   
    private String contactName;

    private String relationship;

    private String emergencyMobileNumber;

    private String alternateNumber;

    private String message;
}