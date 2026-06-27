package com.al.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.al.entity.EmergencyContact;

public interface IEmergencyContactRepository extends JpaRepository<EmergencyContact, UUID>{

}