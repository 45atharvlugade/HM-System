package com.al.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.al.entity.Patient;
import com.al.responsedto.PatientUpdateResponse;
import com.al.service.IPatientSearchAndFilter;

@RestController
@RequestMapping("/api/patients/search")
public class PatientSearchController {

    private final IPatientSearchAndFilter searchService;

    public PatientSearchController(IPatientSearchAndFilter searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<Patient>> searchByFirstName(
            @RequestParam String firstName,
            @RequestParam(defaultValue = "0") int page) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "SEARCH-BY-NAME");

        return ResponseEntity.ok()
                .headers(headers)
                .body(searchService.searchPatientByFirstName(firstName, page));
    }

    @GetMapping("/by-mobile")
    public ResponseEntity<PatientUpdateResponse> getByMobile(
            @RequestParam String mobileNumber) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "SEARCH-BY-MOBILE");

        return ResponseEntity.ok()
                .headers(headers)
                .body(searchService.getPatientByPhoneNumber(mobileNumber));
    }

    @GetMapping("/by-email")
    public ResponseEntity<PatientUpdateResponse> getByEmail(
            @RequestParam String email) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-SERVICE", "PATIENT-SERVICE");
        headers.add("X-ENDPOINT", "SEARCH-BY-EMAIL");

        return ResponseEntity.ok()
                .headers(headers)
                .body(searchService.getPatientByEmail(email));
    }
}