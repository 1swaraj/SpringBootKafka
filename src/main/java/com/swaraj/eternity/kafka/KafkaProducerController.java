package com.swaraj.eternity.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableMongoRepositories(basePackageClasses = DataRepository.class)
@Configuration
@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {
	private final KafkaProducerService producerService;
	private final DataRepository dataRepository;
	
	@Autowired
	public KafkaProducerController(KafkaProducerService producerService,DataRepository dataRepository) {
		this.producerService = producerService;
		this.dataRepository = dataRepository;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producerService.sendMessage(message);
	}
	
	@PostMapping(value = "/createUser")
	public void sendMessageToKafkaTopic(
			@RequestParam("userid") long userId, 
			@RequestParam("firstname") String firstName,
			@RequestParam("lastname") String lastName) {
		
		KafkaData user = new KafkaData();
		user.setUserId(userId);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		this.dataRepository.save(user);
		this.producerService.saveCreateUserLog(user);
	}
	

}
