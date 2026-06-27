package com.al.service;

import java.util.List;

import com.al.entity.Patient;
import com.al.responsedto.PatientUpdateResponse;

public interface IPatientSearchAndFilter {

	public List<Patient> searchPatientByFirstName(String firstName , int page) ;
	
	public PatientUpdateResponse getPatientByPhoneNumber(String mobileNumber);
	
	public PatientUpdateResponse getPatientByEmail(String email);
	
    //public List<Patient> filterPatientBy(PatientSearchAndFilterRequestDTO dto,int page);
    
    
}