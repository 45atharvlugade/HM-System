package com.al.service;

import java.util.List;
import java.util.UUID;

import com.al.entity.Patient;
import com.al.requestdto.PatientUpdateRequest;
import com.al.responsedto.PatientRegisterResponseDTO;
import com.al.responsedto.PatientUpdateResponse;

public interface IPatientProfileManagement {

	public PatientUpdateResponse getPatientByPatientCode(String patientCode);
	
	public List<PatientRegisterResponseDTO> getAllPatient();
	
	public PatientUpdateResponse updatePatientDetails(UUID patientId,PatientUpdateRequest dto);
	
	public String deactivatePatient(String patientCode);
	
}