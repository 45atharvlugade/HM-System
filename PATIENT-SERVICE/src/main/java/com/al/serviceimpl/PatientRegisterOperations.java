package com.al.serviceimpl;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.al.entity.AccountStatus;
import com.al.entity.EmergencyContact;
import com.al.entity.Patient;
import com.al.entity.PatientAddress;
import com.al.entity.Status;
import com.al.exceptions.PatientValueError;
import com.al.repository.IPatientRepository;
import com.al.requestdto.PatientRegisterDTO;
import com.al.requestdto.PatientRegisterRequestDTO;
import com.al.responsedto.PatientRegisterResponseDTO;
import com.al.responsedto.PatientResponseDTO;
import com.al.service.IPatientRegisterOperations;

@Service
public class PatientRegisterOperations implements IPatientRegisterOperations {

	private final IPatientRepository patientRepo;
	
	private final StringRedisTemplate template;
	
	public PatientRegisterOperations(IPatientRepository patientRepo,StringRedisTemplate template) {
		// TODO Auto-generated constructor stub
		this.patientRepo=patientRepo;
		this.template=template;
	}
	
	private String generatePatientCode() {
	    Long id = template.opsForValue().increment("PATIENT_SEQ");
	    return "PAT" + String.format("%05d", id);
	}
	
	@Override
public PatientRegisterResponseDTO registerPatient(PatientRegisterRequestDTO dto) {

		Patient p = new Patient();
		p.setFirstName(dto.getFirstName());
		p.setMiddleName(dto.getMiddleName());
		p.setLastName(dto.getLastName());
		p.setGender(dto.getGender());
		p.setAge(dto.getAge());
		p.setDateOfBirth(dto.getDateOfBirth());
		p.setEmail(dto.getEmail());
		p.setMobileNumber(dto.getMobileNumber());
		p.setBloodGroup(dto.getBloodGroup());
		p.setStatus(Status.ACTIVE);
		p.setAccountStatus(AccountStatus.ACTIVE);
		p.setPatientCode(generatePatientCode());
		
		PatientAddress pa = new PatientAddress();
		pa.setAddressLine1(dto.getAddressLine1());
		pa.setAddressLine2(dto.getAddressLine2());
		pa.setCity(dto.getCity());
		pa.setState(dto.getState());
		pa.setCountry(dto.getCountry());
		pa.setPincode(dto.getPincode());

		pa.setPatient(p);
		p.setPatientAddress(pa);
		
		EmergencyContact ec = new EmergencyContact();
		ec.setContactName(dto.getContactName());
		ec.setRelationship(dto.getRelationship());
		ec.setEmergencyMobileNumber(dto.getEmergencyMobileNumber());
		ec.setAlternateNumber(dto.getAlternateNumber());

		ec.setPatient(p);
		p.setEmergencyContact(ec);
		
		Patient saved = patientRepo.save(p);
		
		PatientRegisterResponseDTO res = new PatientRegisterResponseDTO();

		res.setPatientId(saved.getPatientId());
		res.setPatientCode(saved.getPatientCode());
		res.setFirstName(saved.getFirstName());
		res.setMiddleName(saved.getMiddleName());
		res.setLastName(saved.getLastName());
		res.setEmail(saved.getEmail());
		res.setMobileNumber(saved.getMobileNumber());
		res.setAge(saved.getAge());
		res.setGender(saved.getGender());
		res.setBloodGroup(saved.getBloodGroup());

		if (saved.getPatientAddress() != null) {
		    res.setAddressLine1(saved.getPatientAddress().getAddressLine1());
		    res.setCity(saved.getPatientAddress().getCity());
		    res.setState(saved.getPatientAddress().getState());
		    res.setCountry(saved.getPatientAddress().getCountry());
		    res.setPincode(saved.getPatientAddress().getPincode());
		}

		// EMERGENCY
		if (saved.getEmergencyContact() != null) {
		    res.setContactName(saved.getEmergencyContact().getContactName());
		    res.setRelationship(saved.getEmergencyContact().getRelationship());
		    res.setEmergencyMobileNumber(saved.getEmergencyContact().getEmergencyMobileNumber());
		    res.setAlternateNumber(saved.getEmergencyContact().getAlternateNumber());
		}
		System.out.println("Generated Code: " + p.getPatientCode());
		System.out.println("Saved Code: " + saved.getPatientCode());

		return res;
    
}

	@CachePut(value="patient",key = "#patientId")
	@Override
	public String updateProfilePicture(UUID patientId, String url) {

	    Patient patient = patientRepo.findById(patientId)
	            .orElseThrow(() ->
	                    new PatientValueError(
	                            "Patient not found with id : " + patientId));

	    
	    patient.setProfilePhotoUrl(url);
	    patientRepo.save(patient);
	    return "Profile picture updated successfully";
	}


	@CacheEvict(value = "patients", allEntries = true)
	@Override
	public PatientResponseDTO registerFastPatient(PatientRegisterDTO dto) {

	    if (dto == null) {
	        throw new PatientValueError("Registering patient is Null");
	    }

	    Patient p = new Patient();

	  
	    BeanUtils.copyProperties(dto, p);


	    p.setPatientCode(generatePatientCode());
	    p.setAccountStatus(AccountStatus.ACTIVE);
	    p.setStatus(Status.ACTIVE);

	    Patient saved = patientRepo.save(p);

	    PatientResponseDTO prd = new PatientResponseDTO();
	    BeanUtils.copyProperties(saved, prd);

	    return prd;
	}

}