package org.girardsimon.springkafkastart.service;

import org.girardsimon.springkafkastart.model.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrderGeneratorService {

    private final KafkaProducerService kafkaProducerService;

    public OrderGeneratorService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Scheduled(fixedRate = 5000)
    public void generateAndSendMessage() {
        Order order = new Order(UUID.randomUUID(),
                BigDecimal.valueOf(getRandomValue()).setScale(2, RoundingMode.HALF_UP));
        kafkaProducerService.sendMessage(order);
    }

    public double getRandomValue() {
        return ThreadLocalRandom.current().nextDouble(10, 10000);
    }
}
