package com.al.requestdto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmergencyContactDTO implements Serializable{

	@NotBlank(message = "Contact Name cannot be null or empty")
    private String contactName;
	@NotBlank(message = "relationship cannot be null or empty")
	private String relationship;
	@NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Invalid mobile number"
    )
	private String emergencyMobileNumber;
	@NotBlank(message = "Mobile number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Invalid mobile number"
    )
	private String alternateNumber;
}