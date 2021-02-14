package com.sgarcia.bffgateway.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sgarcia.bffgateway.client.SubcriptionClient;
import com.sgarcia.bffgateway.dto.SubcriptionDTO;

@RestController
@RequestMapping("/api")
public class SubcriptionResource {

	private final Logger log = LoggerFactory.getLogger(SubcriptionResource.class);

	private SubcriptionClient subcriptionClient;

	public SubcriptionResource(SubcriptionClient subcriptionClient) {
		this.subcriptionClient = subcriptionClient;
	}

	@PostMapping("/subcriptions")
	public ResponseEntity<SubcriptionDTO> createSubcription(@Valid @RequestBody SubcriptionDTO subcriptionDTO)
			throws URISyntaxException {
		return subcriptionClient.createSubcription(subcriptionDTO);
	}

	@DeleteMapping("/subcriptions/{id}")
	public ResponseEntity<Void> deleteSubcription(@PathVariable Long id) {
		log.debug("REST request to delete Subcription : {}", id);
		return subcriptionClient.deleteSubcription(id);
	}

	@GetMapping("/subcriptions")
	public List<SubcriptionDTO> getAllSubcriptions() {
		return subcriptionClient.getAllSubcriptions();
	}

	@GetMapping("/subcriptions/{id}")
	public ResponseEntity<SubcriptionDTO> getSubcription(@PathVariable Long id) {
		return subcriptionClient.getSubcription(id);
	}

}
