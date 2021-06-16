package com.swaraj.eternity.kafka;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DataFetchController {

    private DataRepository dataRepository;

    public DataFetchController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping("/all")
    public List<KafkaData> getAll() {
        return dataRepository.findAll();
    }
}
