package com.al.repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.al.entity.Patient;

public interface  IPatientRepository extends JpaRepository<Patient, UUID> {

	public Optional<Patient> findByPatientCode(String patientCode);
	
	public boolean existsByPatientCode(String patientCode);
	
	@Query("SELECT p FROM Patient p WHERE p.firstName LIKE CONCAT('%', :firstName, '%')")
	public List<Patient>findAllSamePatientByFirstName( @Param("firstName") String firstName, Pageable pageable);
	
	@Query("SELECT p FROM Patient p WHERE p.firstName LIKE CONCAT('%', :lastName, '%')")
	public List<Patient> findAllSamePatientByLastName( @Param("firstName") String lastName, Pageable pageable);
	
	@Query("SELECT p FROM Patient p WHERE p.mobileNumber=:mobileNumber")
	public Optional<Patient> findByMobileNumber(@Param("mobileNumber") String mobileNumber);
	
	@Query("SELECT p FROM Patient p  WHERE p.email=:email")
	public Optional<Patient> findByEmail(@Param("email") String email);
	
}