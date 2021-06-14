package com.swaraj.eternity.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducerService 
{
	private static final Logger logger = 
			LoggerFactory.getLogger(KafkaProducerService.class);
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(String message) 
	{
		logger.info(String.format("Message sent -> %s", message));
		this.kafkaTemplate.send("dummy", message);
	}
	
	public void saveCreateUserLog(KafkaData user) 
	{
		logger.info(String.format("User created -> %s", user));
		this.kafkaTemplate.send("user", user);
	}
}
