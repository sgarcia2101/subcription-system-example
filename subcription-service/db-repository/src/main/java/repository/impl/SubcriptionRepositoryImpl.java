package repository.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import domain.Subcription;
import entity.SubcriptionEntity;
import mapper.SubcriptionMapper;
import repository.SubcriptionJpaRepository;
import repository.SubcriptionRepository;

@Repository
public class SubcriptionRepositoryImpl implements SubcriptionRepository {

    private final Logger log = LoggerFactory.getLogger(SubcriptionRepositoryImpl.class);

	SubcriptionJpaRepository subcriptionJpaRepository;
	
	SubcriptionMapper subcriptionMapper;
	
	public SubcriptionRepositoryImpl(SubcriptionJpaRepository subcriptionJpaRepository, SubcriptionMapper subcriptionMapper) {
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
