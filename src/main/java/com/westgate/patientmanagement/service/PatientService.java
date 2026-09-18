package com.westgate.patientmanagement.service;

import com.westgate.patientmanagement.dto.PatientRequest;
import com.westgate.patientmanagement.dto.PatientResponse;

import java.util.List;

public interface PatientService {

	PatientResponse createPatient(PatientRequest request);

	PatientResponse getPatientById(Long id);

	List<PatientResponse> getAllPatients();

	PatientResponse updatePatient(Long id, PatientRequest request);

	void deletePatient(Long id);
}