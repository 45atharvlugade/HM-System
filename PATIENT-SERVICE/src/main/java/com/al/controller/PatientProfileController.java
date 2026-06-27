package com.al.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.al.entity.Patient;
import com.al.requestdto.PatientUpdateRequest;
import com.al.responsedto.PatientRegisterResponseDTO;
import com.al.responsedto.PatientUpdateResponse;
import com.al.service.IPatientProfileManagement;


@RestController
@RequestMapping("/api/patients/profile")
public class PatientProfileController {

    private final IPatientProfileManagement profileService;

    public PatientProfileController(IPatientProfileManagement profileService) {
        this.profileService = profileService;
    }

    
    @GetMapping("/{patientCode}")
    public ResponseEntity<PatientUpdateResponse> getPatientByCode(@PathVariable String patientCode) {
        return ResponseEntity.ok(profileService.getPatientByPatientCode(patientCode));
    }

    @GetMapping
    public ResponseEntity<List<PatientRegisterResponseDTO>> getAllPatients() {
        return ResponseEntity.ok(profileService.getAllPatient());
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientUpdateResponse> updatePatient(
            @PathVariable UUID patientId,
            @RequestBody PatientUpdateRequest dto) {

        return ResponseEntity.ok(profileService.updatePatientDetails(patientId, dto));
    }

    @DeleteMapping("/{patientCode}")
    public ResponseEntity<String> deactivatePatient(@PathVariable String patientCode) {
        return ResponseEntity.ok(profileService.deactivatePatient(patientCode));
    }
}