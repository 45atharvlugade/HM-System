package com.al.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.al.entity.AccountStatus;
import com.al.entity.Patient;
import com.al.entity.Status;
import com.al.repository.IPatientRepository;
import com.al.responsedto.PatientUpdateResponse;
import com.al.service.IPatientStatusOperations;

@Service
public class PatientStatusOperations implements IPatientStatusOperations {

	
    private final IPatientRepository patientRepository;
    
    public PatientStatusOperations(IPatientRepository patientRepository) {
    	this.patientRepository=patientRepository;
		// TODO Auto-generated constructor stub
	}

    @CachePut(value = "patient",key = "#patientCode")
    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public PatientUpdateResponse updatePatientStatus(String patientCode, Status status) {

        Patient patient = patientRepository.findByPatientCode(patientCode)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + patientCode));

        patient.setStatus(status);

        patientRepository.save(patient);

       PatientUpdateResponse pr=new PatientUpdateResponse();
       BeanUtils.copyProperties(patient, pr);
       return pr;
    }
    
    
    @CachePut(value = "patient", key = "#patientCode")
    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public PatientUpdateResponse updateAccountStatus(String patientCode, AccountStatus status) {

        Patient patient = patientRepository.findByPatientCode(patientCode)
                .orElseThrow(() -> new RuntimeException("Patient not found: " + patientCode));

        patient.setAccountStatus(status);

        patientRepository.save(patient);

        PatientUpdateResponse pr=new PatientUpdateResponse();
        BeanUtils.copyProperties(patient, pr);
        return pr;
    }
}