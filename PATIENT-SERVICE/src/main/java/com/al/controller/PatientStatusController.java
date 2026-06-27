package com.al.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.al.entity.AccountStatus;
import com.al.entity.Status;
import com.al.responsedto.PatientUpdateResponse;
import com.al.service.IPatientStatusOperations;

@RestController
@RequestMapping("/api/patients/status")
public class PatientStatusController {

    private final IPatientStatusOperations statusService;

    public PatientStatusController(IPatientStatusOperations statusService) {
        this.statusService = statusService;
    }

    @PutMapping("/{patientCode}")
    public ResponseEntity<PatientUpdateResponse> updateStatus(
            @PathVariable String patientCode,
            @RequestParam Status status) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "UPDATE-PATIENT-STATUS");

        return ResponseEntity.ok()
                .headers(headers)
                .body(statusService.updatePatientStatus(patientCode, status));
    }

    @PutMapping("/{patientCode}/account")
    public ResponseEntity<PatientUpdateResponse> updateAccountStatus(
            @PathVariable String patientCode,
            @RequestParam AccountStatus status) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "UPDATE-ACCOUNT-STATUS");

        return ResponseEntity.ok()
                .headers(headers)
                .body(statusService.updateAccountStatus(patientCode, status));
    }
}