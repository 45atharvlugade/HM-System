package com.al.requestdto;

import java.io.Serializable;

import com.al.entity.BloodGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PatientUpdateRequest implements Serializable{

   @NotBlank(message = "Mobile number is required")
   @Pattern(
	        regexp = "^[6-9]\\d{9}$",
	        message = "Please provide a valid emergency mobile number"
	)
	private String mobileNumber;
	
	@Email(message = "not a valid email")
	private String email;
	
	
	private BloodGroup bloodGroup;
	 
}