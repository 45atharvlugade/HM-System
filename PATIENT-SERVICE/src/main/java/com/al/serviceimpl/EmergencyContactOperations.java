package com.al.serviceimpl;

import org.springframework.stereotype.Service;

import com.al.entity.EmergencyContact;
import com.al.entity.Patient;
import com.al.exceptions.PatientValueError;
import com.al.repository.IPatientRepository;
import com.al.requestdto.EmergencyContactDTO;
import com.al.service.IEmergencyContactOperations;

@Service
public class EmergencyContactOperations implements IEmergencyContactOperations {

    private final IPatientRepository patientRepo;

    public EmergencyContactOperations(IPatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }


    @Override
    public String addEmergencyContact(String patientCode, EmergencyContactDTO dto) {

        Patient patient = patientRepo.findByPatientCode(patientCode)
                .orElseThrow(() ->
                        new PatientValueError("Patient not found: " + patientCode));


        if (patient.getEmergencyContact() != null) {
            throw new PatientValueError("Emergency contact already exists for this patient");
        }

        EmergencyContact contact = new EmergencyContact();
        contact.setContactName(dto.getContactName());
        contact.setRelationship(dto.getRelationship());
        contact.setEmergencyMobileNumber(dto.getEmergencyMobileNumber());
        contact.setAlternateNumber(dto.getAlternateNumber());


        contact.setPatient(patient);
        patient.setEmergencyContact(contact);

        patientRepo.save(patient);

        return "Emergency contact added successfully";
    }


    @Override
    public String updateEmergencyContact(String patientCode, EmergencyContactDTO dto) {

        Patient patient = patientRepo.findByPatientCode(patientCode)
                .orElseThrow(() ->
                        new PatientValueError("Patient not found: " + patientCode));

        EmergencyContact contact = patient.getEmergencyContact();

        if (contact == null) {
            throw new PatientValueError("Emergency contact not found for this patient");
        }


        if (dto.getContactName() != null) {
            contact.setContactName(dto.getContactName());
        }

        if (dto.getRelationship() != null) {
            contact.setRelationship(dto.getRelationship());
        }

        if (dto.getEmergencyMobileNumber() != null) {
            contact.setEmergencyMobileNumber(dto.getEmergencyMobileNumber());
        }

        if (dto.getAlternateNumber() != null) {
            contact.setAlternateNumber(dto.getAlternateNumber());
        }

        patientRepo.save(patient);

        return "Emergency contact updated successfully";
    }
}