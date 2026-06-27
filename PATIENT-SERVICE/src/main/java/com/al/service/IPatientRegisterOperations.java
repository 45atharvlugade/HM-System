package com.al.service;

import java.util.UUID;

import com.al.requestdto.PatientRegisterDTO;
import com.al.requestdto.PatientRegisterRequestDTO;
import com.al.responsedto.PatientRegisterResponseDTO;
import com.al.responsedto.PatientResponseDTO;

public interface IPatientRegisterOperations {

	public PatientRegisterResponseDTO registerPatient(PatientRegisterRequestDTO dto);
	
	public PatientResponseDTO registerFastPatient(PatientRegisterDTO dto);;
	
	public String updateProfilePicture(UUID patientId,String url);
	
}