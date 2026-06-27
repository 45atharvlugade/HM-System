package com.al.responsedto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.al.entity.Gender;
import com.al.entity.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDTO implements Serializable{

    private UUID patientId;

    private String patientCode;

    private String firstName;

    private String middleName;

    private String lastName;

    private Gender gender;

    private String bloodGroup;

    private String email;

    private String mobileNumber;
    
    private Integer age;

    private Status status;

    private LocalDateTime createdAt;
}