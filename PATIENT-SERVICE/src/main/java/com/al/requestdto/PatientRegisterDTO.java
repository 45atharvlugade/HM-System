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
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegisterDTO implements Serializable{

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
    
    private Status status;
}
