package com.sgarcia.bffgateway.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sgarcia.bffgateway.dto.SubcriptionDTO;

@FeignClient(name = "subcription-service", url = "localhost:8081")
public interface SubcriptionClient {

	@PostMapping("/api/subcriptions")
	public ResponseEntity<SubcriptionDTO> createSubcription(@Valid @RequestBody SubcriptionDTO subcriptionDTO);

	@DeleteMapping("/api/subcriptions/{id}")
	public ResponseEntity<Void> deleteSubcription(@PathVariable Long id);

	@GetMapping("/api/subcriptions")
	public List<SubcriptionDTO> getAllSubcriptions();

	@GetMapping("/api/subcriptions/{id}")
	public ResponseEntity<SubcriptionDTO> getSubcription(@PathVariable Long id);

}
