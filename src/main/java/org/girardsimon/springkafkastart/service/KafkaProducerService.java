package org.girardsimon.springkafkastart.service;

import org.girardsimon.protobuf.OrderOuterClass;
import org.girardsimon.springkafkastart.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger LOG =
            LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, OrderOuterClass.Order> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderOuterClass.Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Order order) {
        LOG.info("Sending message with key {} to Kafka", order);
        OrderOuterClass.Order orderSchema = OrderOuterClass.Order.newBuilder()
                .setOrderId(order.orderId().toString())
                .setPrice(order.price().toPlainString())
                .build();
        kafkaTemplate.send(topicName, order.orderId().toString(), orderSchema);
    }
}
