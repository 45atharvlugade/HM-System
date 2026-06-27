package com.al.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.al.entity.PatientAddress;

public interface IPatientAddressRepository extends JpaRepository<PatientAddress, UUID> {

}