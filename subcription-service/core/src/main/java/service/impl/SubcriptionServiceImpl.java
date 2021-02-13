package service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import domain.Subcription;
import exception.SubcriptionBadRequestException;
import exception.SubcriptionNotFoundException;
import repository.SubcriptionNotificationRepository;
import repository.SubcriptionRepository;
import service.SubcriptionService;

@Service
public class SubcriptionServiceImpl implements SubcriptionService {

	private SubcriptionRepository subcriptionRepository;

	private SubcriptionNotificationRepository notificationRepository;

	public SubcriptionServiceImpl(SubcriptionRepository subcriptionRepository,
			SubcriptionNotificationRepository notificationRepository) {
		this.subcriptionRepository = subcriptionRepository;
		this.notificationRepository = notificationRepository;
	}

	@Override
	public Subcription save(Subcription subcription) {
		
        if (subcription.getId() != null) {
            throw new SubcriptionBadRequestException("A new subcription cannot already have an ID");
        }

		Subcription savedSubcription = subcriptionRepository.save(subcription);

		notificationRepository.sendNew(subcription);

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
