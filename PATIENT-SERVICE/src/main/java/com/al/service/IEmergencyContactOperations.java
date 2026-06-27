package com.al.service;

import com.al.requestdto.EmergencyContactDTO;

public interface IEmergencyContactOperations {

	public String addEmergencyContact(String patientCode,EmergencyContactDTO dto);
	
	public String updateEmergencyContact(String patientCode,EmergencyContactDTO dto);
	
	
}