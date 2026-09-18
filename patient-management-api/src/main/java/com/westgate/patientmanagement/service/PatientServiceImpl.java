package com.westgate.patientmanagement.service;

import com.westgate.patientmanagement.dto.PatientRequest;
import com.westgate.patientmanagement.dto.PatientResponse;
import com.westgate.patientmanagement.entity.Patient;
import com.westgate.patientmanagement.exception.PatientNotFoundException;
import com.westgate.patientmanagement.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public PatientResponse createPatient(PatientRequest request) {

		// Business validation
		if (request.getEmail() != null && patientRepository.findByEmail(request.getEmail()).isPresent()) {

			throw new IllegalArgumentException("Patient with this email already exists");
		}

		Patient patient = new Patient();

		patient.setFirstName(request.getFirstName());
		patient.setLastName(request.getLastName());
		patient.setDateOfBirth(request.getDateOfBirth());
		patient.setGender(request.getGender());
		patient.setPhone(request.getPhone());
		patient.setEmail(request.getEmail());
		patient.setAddress(request.getAddress());

		Patient savedPatient = patientRepository.save(patient);

		return convertToResponse(savedPatient);
	}

	@Override
	public PatientResponse getPatientById(Long id) {

		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

		return convertToResponse(patient);
	}

	@Override
	public List<PatientResponse> getAllPatients() {

		return patientRepository.findAll().stream().map(this::convertToResponse).toList();
	}

	@Override
	public PatientResponse updatePatient(Long id, PatientRequest request) {

		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

		patient.setFirstName(request.getFirstName());
		patient.setLastName(request.getLastName());
		patient.setDateOfBirth(request.getDateOfBirth());
		patient.setGender(request.getGender());
		patient.setPhone(request.getPhone());
		patient.setEmail(request.getEmail());
		patient.setAddress(request.getAddress());

		Patient updatedPatient = patientRepository.save(patient);

		return convertToResponse(updatedPatient);
	}

	@Override
	public void deletePatient(Long id) {

		Patient patient = patientRepository.findById(id)
				.orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));

		patientRepository.delete(patient);
	}

	private PatientResponse convertToResponse(Patient patient) {

		return new PatientResponse(patient.getId(), patient.getFirstName(), patient.getLastName(),
				patient.getDateOfBirth(), patient.getGender(), patient.getPhone(), patient.getEmail(),
				patient.getAddress());
	}
}