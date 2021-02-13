package com.sgarcia.subcription.repository.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sgarcia.subcription.domain.Subcription;
import com.sgarcia.subcription.entity.SubcriptionEntity;
import com.sgarcia.subcription.mapper.SubcriptionEntityMapper;
import com.sgarcia.subcription.repository.SubcriptionJpaRepository;
import com.sgarcia.subcription.repository.SubcriptionRepository;

@Repository
public class SubcriptionRepositoryImpl implements SubcriptionRepository {

    private final Logger log = LoggerFactory.getLogger(SubcriptionRepositoryImpl.class);

	SubcriptionJpaRepository subcriptionJpaRepository;
	
	SubcriptionEntityMapper subcriptionMapper;
	
	public SubcriptionRepositoryImpl(SubcriptionJpaRepository subcriptionJpaRepository, SubcriptionEntityMapper subcriptionMapper) {
		this.subcriptionJpaRepository = subcriptionJpaRepository;
		this.subcriptionMapper = subcriptionMapper;
	}

	@Override
	public Subcription save(Subcription subcription) {
        log.debug("Save subcription: {}", subcription);
        
		SubcriptionEntity entity = subcriptionMapper.subcriptionToSubcriptioEntity(subcription);
		
		SubcriptionEntity savedEntity = subcriptionJpaRepository.save(entity);
		
		return subcriptionMapper.subcriptionEntityToSubcription(savedEntity);
	}

	@Override
	public void delete(Long id) {
        log.debug("Delete subcription with id: {}", id);
		subcriptionJpaRepository.deleteById(id);
	}

	@Override
	public Optional<Subcription> get(Long id) {
        log.debug("Get subcription with id: {}", id);
		return subcriptionJpaRepository.findById(id)
	            .map(subcriptionMapper::subcriptionEntityToSubcription);
	}

	@Override
	public List<Subcription> getAll() {
        log.debug("Get all subcriptions");
        return subcriptionJpaRepository.findAll().stream()
                .map(subcriptionMapper::subcriptionEntityToSubcription)
                .collect(Collectors.toCollection(LinkedList::new));
	}

}
