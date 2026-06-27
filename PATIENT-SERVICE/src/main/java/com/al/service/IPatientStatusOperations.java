package com.al.service;

import com.al.entity.AccountStatus;
import com.al.entity.Status;
import com.al.responsedto.PatientUpdateResponse;

public interface IPatientStatusOperations {

	PatientUpdateResponse updatePatientStatus(String patientCode, Status status);

    PatientUpdateResponse updateAccountStatus(String patientCode, AccountStatus status);
	
    
}