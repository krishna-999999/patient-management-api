package com.westgate.patientmanagement.controller;

import com.westgate.patientmanagement.dto.PatientRequest;
import com.westgate.patientmanagement.dto.PatientResponse;
import com.westgate.patientmanagement.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	// CREATE
	@PostMapping
	public ResponseEntity<PatientResponse> createPatient(@Valid @RequestBody PatientRequest request) {

		PatientResponse response = patientService.createPatient(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	// GET BY ID
	@GetMapping("/{id}")
	public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {

		return ResponseEntity.ok(patientService.getPatientById(id));
	}

	// GET ALL
	@GetMapping
	public ResponseEntity<List<PatientResponse>> getAllPatients() {

		return ResponseEntity.ok(patientService.getAllPatients());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id,
			@Valid @RequestBody PatientRequest request) {

		return ResponseEntity.ok(patientService.updatePatient(id, request));
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id) {

		patientService.deletePatient(id);

		return ResponseEntity.noContent().build();
	}
}