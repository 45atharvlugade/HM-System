package com.al.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.al.requestdto.EmergencyContactDTO;
import com.al.service.IEmergencyContactOperations;

@RestController
@RequestMapping("/api/patients/emergency-contact")
public class EmergencyContactController {

    private final IEmergencyContactOperations emergencyService;

    public EmergencyContactController(IEmergencyContactOperations emergencyService) {
        this.emergencyService = emergencyService;
    }

    @PostMapping("/{patientCode}")
    public ResponseEntity<String> addEmergencyContact(
            @PathVariable String patientCode,
            @RequestBody EmergencyContactDTO dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "ADD-EMERGENCY-CONTACT");

        return ResponseEntity.ok()
                .headers(headers)
                .body(emergencyService.addEmergencyContact(patientCode, dto));
    }

    @PutMapping("/{patientCode}")
    public ResponseEntity<String> updateEmergencyContact(
            @PathVariable String patientCode,
            @RequestBody EmergencyContactDTO dto) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "UPDATE-EMERGENCY-CONTACT");

        return ResponseEntity.ok()
                .headers(headers)
                .body(emergencyService.updateEmergencyContact(patientCode, dto));
    }
}