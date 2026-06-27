package com.al.requestdto;

import java.io.Serializable;
import java.time.LocalDate;

import com.al.entity.BloodGroup;
import com.al.entity.Gender;
import com.al.entity.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegisterRequestDTO implements Serializable{

	@NotBlank(message = "first cannot be null or empty")
	private String firstName;
	
	
	private String middleName;
	
	@NotBlank(message = "last Name cannot be null or empty")
	private String lastName;
	
    @NotNull(message = "gender is required")
	private Gender gender;
	
    @NotNull(message = "Birth Date is required")
    private LocalDate dateOfBirth;
    
    @NotNull(message = "Blood group is required")
    private BloodGroup bloodGroup;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Invalid mobile number"
    )
    private String mobileNumber;
   
    
    @NotBlank(message = "Address is requried")
    private String addressLine1;
  
    private String addressLine2;
    
    @NotBlank(message = "City is requried")
    private String city;
    
    @NotBlank(message = "age required")
    private Integer age;
    
    @NotBlank(message = "State is requried")
    private String state;
    
    @NotBlank(message = "Country is requried")
    private String country;
    
    @NotBlank(message = "pincode required")
    @NotBlank(message = "Pincode is required")
    @Pattern(
        regexp = "^[1-9][0-9]{5}$",
        message = "Invalid pincode"
    )
    private String pincode;
    
    @NotBlank(message = "Emergency contact name is required")
    private String contactName;

    @NotBlank(message = "Relationship is required")
    private String relationship;

    @NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Please provide a valid emergency mobile number"
    )
    private String emergencyMobileNumber;

    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Please provide a valid alternate mobile number"
    )
    private String alternateNumber;
}