package com.al.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.al.entity.Patient;
import com.al.repository.IPatientRepository;
import com.al.responsedto.PatientUpdateResponse;
import com.al.service.IPatientSearchAndFilter;

@Service
public class PatientSearchAndFilter implements IPatientSearchAndFilter {

	
	private final IPatientRepository patientRepo;
	
	public PatientSearchAndFilter(IPatientRepository patientRepo) {
		this.patientRepo=patientRepo;
		// TODO Auto-generated constructor stub
	}
	
	private static final int pageSize=20;
	
	@Cacheable(value = "patients",key = "#firstName + '-' +#page")
	@Override
	public List<Patient> searchPatientByFirstName(String firstName,int page) {
		
		if(firstName==null) {
			return null;
		}
		
		Pageable pageable=PageRequest.of(page, pageSize);
		 List<Patient> list=patientRepo.findAllSamePatientByFirstName(firstName, pageable);
		 
		 if(list.isEmpty()) {
			 return Collections.emptyList();
		 }
		return list;
	}

	@Cacheable(value = "patient",key = "#mobileNumber")
	@Override
	public PatientUpdateResponse getPatientByPhoneNumber(String mobileNumber) {
		 Patient patient = patientRepo.findByMobileNumber(mobileNumber)
		            .orElseThrow(() -> new RuntimeException("Patient not found with mobile: " + mobileNumber));

		    PatientUpdateResponse pr = new PatientUpdateResponse();
		    BeanUtils.copyProperties(patient, pr);

		    return pr;
	}

	@Cacheable(value = "patient",key = "#email")
	@Override
	public PatientUpdateResponse getPatientByEmail(String email) {
		Patient p=patientRepo.findByEmail(email)
				.orElseThrow(()->new RuntimeException("Patient Not Found : "+email));
		
		PatientUpdateResponse pr=new PatientUpdateResponse();
		BeanUtils.copyProperties(p, pr);
		return pr;
	}

	/*
	 * @Override public List<Patient>
	 * filterPatientBy(PatientSearchAndFilterRequestDTO dto,int pageNumber) {
	 * 
	 * Patient p=new Patient(); BeanUtils.copyProperties(dto, p);
	 * 
	 * 
	 * 
	 * Pageable pageable=PageRequest.of(pageNumber, pageSize);
	 * 
	 * List<Patient> list=patientRepo.findAll(p);
	 * 
	 * return ; }
	 */

	
}