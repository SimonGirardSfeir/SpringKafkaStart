package org.girardsimon.springkafkastart.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.girardsimon.protobuf.OrderOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "myorders", groupId = "myorders-consumer", concurrency = "3")
    public void consume(ConsumerRecord<String, OrderOuterClass.Order> orderMessage) {
        OrderOuterClass.Order order = orderMessage.value();
        LOG.info("Received order with id: {} and price {} CHF", order.getOrderId(), order.getPrice());
        LOG.info("It comes from partition: {}", orderMessage.partition());
    }
}
