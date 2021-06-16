package com.swaraj.eternity.kafka;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<KafkaData, Integer> {
}
