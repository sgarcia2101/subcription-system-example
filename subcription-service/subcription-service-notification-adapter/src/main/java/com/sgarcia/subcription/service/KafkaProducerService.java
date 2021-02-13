package com.sgarcia.subcription.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgarcia.subcription.domain.Subcription;

@Service
public class KafkaProducerService implements SubcriptionNotificationService {

	private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private final String TOPIC = "kafkaTopic";

	@Override
	public void sendNotification(Subcription subcription) {
		
		String message;
		try {
			message = new ObjectMapper().writeValueAsString(subcription);
		} catch (JsonProcessingException e) {
			log.error("Error serializing subcription: {}", subcription);
			return;
		}

		log.info("Producing message: {}", message);

		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC, message);

		future.addCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message=[ {} ] due to : {}", subcription, ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Sent message=[ {} ] with offset=[ {} ]", subcription, result.getRecordMetadata().offset());
			}
		});
	}
}
