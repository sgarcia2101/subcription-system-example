package com.sgarcia.mailingservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgarcia.mailingservice.domain.Subcription;

@Service
public final class ConsumerService {

	private static final Logger log = LoggerFactory.getLogger(ConsumerService.class);

	@KafkaListener(topics = "kafkaTopic", groupId = "group_id")
	public void consume(String message) {

		Subcription subcription;
		try {
			subcription = new ObjectMapper().readValue(message, Subcription.class);
		} catch (JsonProcessingException e) {
			log.error("Error deserializing message: {}", message);
			return;
		}

		log.info("Consumed Subcription: {}", subcription);
	}
}