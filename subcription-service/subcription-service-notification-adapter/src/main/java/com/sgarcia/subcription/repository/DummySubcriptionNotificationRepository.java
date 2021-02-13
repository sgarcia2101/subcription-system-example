package com.sgarcia.subcription.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sgarcia.subcription.domain.Subcription;

@Repository
public class DummySubcriptionNotificationRepository implements SubcriptionNotificationRepository {

    private final Logger log = LoggerFactory.getLogger(DummySubcriptionNotificationRepository.class);

	@Override
	public void sendNew(Subcription subcription) {
        log.info("Sending notification of subcription: {}", subcription);
	}

}
