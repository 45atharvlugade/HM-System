package com.al.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.al.requestdto.PatientRegisterDTO;
import com.al.requestdto.PatientRegisterRequestDTO;
import com.al.responsedto.PatientRegisterResponseDTO;
import com.al.responsedto.PatientResponseDTO;
import com.al.service.IPatientRegisterOperations;

@RestController
@RequestMapping("/api/patients")
public class PatientRegistrationController {

    private final IPatientRegisterOperations patientService;

    public PatientRegistrationController(IPatientRegisterOperations patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<PatientRegisterResponseDTO> registerPatient(
            @RequestBody PatientRegisterRequestDTO dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "REGISTER-PATIENT");

        return ResponseEntity.ok()
                .headers(headers)
                .body(patientService.registerPatient(dto));
    }

    @PostMapping("/register-fast")
    public ResponseEntity<PatientResponseDTO> registerFastPatient(
            @RequestBody PatientRegisterDTO dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "REGISTER-FAST-PATIENT");

        return ResponseEntity.ok()
                .headers(headers)
                .body(patientService.registerFastPatient(dto));
    }

    @PutMapping("/{patientId}/profile-picture")
    public ResponseEntity<String> updateProfilePicture(
            @PathVariable UUID patientId,
            @RequestParam String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "UPDATE-PROFILE-PICTURE");

        return ResponseEntity.ok()
                .headers(headers)
                .body(patientService.updateProfilePicture(patientId, url));
    }
}