package controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import domain.Subcription;
import dto.SubcriptionDTO;
import exception.SubcriptionBadRequestException;
import exception.SubcriptionNotFoundException;
import mapper.SubcriptionDtoMapper;
import service.SubcriptionService;

@RestController
@RequestMapping("/api")
public class SubcriptionResource {

	private final Logger log = LoggerFactory.getLogger(SubcriptionResource.class);

	private SubcriptionService subcriptionService;

	private SubcriptionDtoMapper subcriptionDtoMapper;

	public SubcriptionResource(SubcriptionService subcriptionService, SubcriptionDtoMapper subcriptionDtoMapper) {
		this.subcriptionService = subcriptionService;
		this.subcriptionDtoMapper = subcriptionDtoMapper;
	}

	@PostMapping("/subcriptions")
	public ResponseEntity<SubcriptionDTO> createSubcription(@Valid @RequestBody SubcriptionDTO subcriptionDTO)
			throws URISyntaxException {
		log.debug("REST request to save Subcription : {}", subcriptionDTO);

		Subcription subcription = subcriptionDtoMapper.subcriptionDtoToSubcription(subcriptionDTO);
		subcription = subcriptionService.save(subcription);
		SubcriptionDTO resultDto = subcriptionDtoMapper.subcriptionToSubcriptioDto(subcription);

		return ResponseEntity.created(new URI("/api/subcriptions/" + resultDto.getId())).body(resultDto);
	}

	@DeleteMapping("/subcriptions/{id}")
	public ResponseEntity<Void> deleteSubcription(@PathVariable Long id) {
		log.debug("REST request to delete Subcription : {}", id);
		subcriptionService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/subcriptions")
	public List<SubcriptionDTO> getAllSubcriptions() {
		log.debug("REST request to get all Subcriptions");

		return subcriptionService.getAll().stream().map(subcriptionDtoMapper::subcriptionToSubcriptioDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@GetMapping("/subcriptions/{id}")
	public ResponseEntity<SubcriptionDTO> getSubcription(@PathVariable Long id) {
		log.debug("REST request to get Subcription : {}", id);

		Optional<SubcriptionDTO> subcriptionDTO = subcriptionService.get(id)
				.map(subcriptionDtoMapper::subcriptionToSubcriptioDto);

		return subcriptionDTO.map(response -> ResponseEntity.ok().body(response))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@ExceptionHandler({ SubcriptionNotFoundException.class })
	public ResponseEntity<Object> handleSubcriptionNotFoundException(Exception exception) {
		return new ResponseEntity<Object>(
				exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ SubcriptionBadRequestException.class })
	public ResponseEntity<Object> handleSubcriptionBadRequestException(Exception exception) {
		return new ResponseEntity<Object>(
				exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
