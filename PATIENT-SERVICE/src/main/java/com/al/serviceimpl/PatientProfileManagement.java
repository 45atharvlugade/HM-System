package com.al.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.al.entity.AccountStatus;
import com.al.entity.Patient;
import com.al.exceptions.PatientValueError;
import com.al.repository.IPatientRepository;
import com.al.requestdto.PatientUpdateRequest;
import com.al.responsedto.PatientRegisterResponseDTO;
import com.al.responsedto.PatientUpdateResponse;
import com.al.service.IPatientProfileManagement;

@Service
public class PatientProfileManagement implements IPatientProfileManagement {

    private final IPatientRepository patientRepo;

    public PatientProfileManagement(IPatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }


    @Cacheable(value = "patient", key = "#patientCode")
    @Override
    public PatientUpdateResponse getPatientByPatientCode(String patientCode) {

        Patient patient = patientRepo.findByPatientCode(patientCode)
                .orElseThrow(() ->
                        new PatientValueError("Patient not found: " + patientCode));

        PatientUpdateResponse pr = new PatientUpdateResponse();
        BeanUtils.copyProperties(patient, pr);

        return pr;
    }


    @Cacheable(value = "patients")
    @Override
    public List<PatientRegisterResponseDTO> getAllPatient() {

        List<Patient> list = patientRepo.findAll();

        List<PatientRegisterResponseDTO> responseList = new ArrayList<>();

        list.forEach(row -> {

            PatientRegisterResponseDTO dto = new PatientRegisterResponseDTO();


            dto.setPatientId(row.getPatientId());
            dto.setPatientCode(row.getPatientCode());
            dto.setFirstName(row.getFirstName());
            dto.setMiddleName(row.getMiddleName());
            dto.setLastName(row.getLastName());
            dto.setAge(row.getAge());
            dto.setGender(row.getGender());
            dto.setBloodGroup(row.getBloodGroup());


            dto.setEmail(row.getEmail());
            dto.setMobileNumber(row.getMobileNumber());

            if (row.getEmergencyContact() != null) {
                dto.setContactName(row.getEmergencyContact().getContactName());
                dto.setAlternateNumber(row.getEmergencyContact().getAlternateNumber());
                dto.setEmergencyMobileNumber(row.getEmergencyContact().getEmergencyMobileNumber());
                dto.setRelationship(row.getEmergencyContact().getRelationship());
            }

            if (row.getPatientAddress() != null) {
                dto.setAddressLine1(row.getPatientAddress().getAddressLine1());
                dto.setAddressLine2(row.getPatientAddress().getAddressLine2());
                dto.setCity(row.getPatientAddress().getCity());
                dto.setState(row.getPatientAddress().getState());
                dto.setCountry(row.getPatientAddress().getCountry());
                dto.setPincode(row.getPatientAddress().getPincode());
            }
            dto.setStatus(row.getStatus());
            dto.setCreatedAt(row.getCreatedAt());

            responseList.add(dto);
        });

        return responseList;
    }


    @CachePut(value = "patient", key = "#patientId")
    @CacheEvict(value = "patients", allEntries = true)
    @Override
    public PatientUpdateResponse updatePatientDetails(UUID patientId, PatientUpdateRequest dto) {

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() ->
                        new PatientValueError("Patient not found: " + patientId));

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            patient.setEmail(dto.getEmail());
        }

        if (dto.getBloodGroup() != null) {
            patient.setBloodGroup(dto.getBloodGroup());
        }

        if (dto.getMobileNumber() != null && !dto.getMobileNumber().isBlank()) {
            patient.setMobileNumber(dto.getMobileNumber());
        }

        patientRepo.save(patient);

        PatientUpdateResponse response = new PatientUpdateResponse();
        BeanUtils.copyProperties(patient, response);

        return response;
    }


    @CacheEvict(value = { "patient", "patients" }, allEntries = true)
    @Override
    public String deactivatePatient(String patientCode) {

        Patient patient = patientRepo.findByPatientCode(patientCode)
                .orElseThrow(() ->
                        new PatientValueError("Patient not found: " + patientCode));

        patient.setAccountStatus(AccountStatus.DELETED);

        patientRepo.save(patient);

        return "Account Deleted Successfully";
    }
}