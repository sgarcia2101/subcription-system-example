package com.sgarcia.subcription.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sgarcia.subcription.domain.Subcription;
import com.sgarcia.subcription.exception.SubcriptionBadRequestException;
import com.sgarcia.subcription.exception.SubcriptionNotFoundException;
import com.sgarcia.subcription.repository.SubcriptionRepository;
import com.sgarcia.subcription.service.SubcriptionNotificationService;
import com.sgarcia.subcription.service.SubcriptionService;

@Service
public class SubcriptionServiceImpl implements SubcriptionService {

	private SubcriptionRepository subcriptionRepository;

	private SubcriptionNotificationService notificationRepository;

	public SubcriptionServiceImpl(SubcriptionRepository subcriptionRepository,
			SubcriptionNotificationService notificationRepository) {
		this.subcriptionRepository = subcriptionRepository;
		this.notificationRepository = notificationRepository;
	}

	@Override
	public Subcription save(Subcription subcription) {
		
        if (subcription.getId() != null) {
            throw new SubcriptionBadRequestException("A new subcription cannot already have an ID");
        }

		Subcription savedSubcription = subcriptionRepository.save(subcription);

		notificationRepository.sendNotification(savedSubcription);

		return savedSubcription;
	}

	@Override
	public void delete(Long id) {
		if (this.get(id).isEmpty()) {
			throw new SubcriptionNotFoundException(id);
		}
		subcriptionRepository.delete(id);
	}

	@Override
	public Optional<Subcription> get(Long id) {
		return subcriptionRepository.get(id);
	}

	@Override
	public List<Subcription> getAll() {
		return subcriptionRepository.getAll();
	}

}
