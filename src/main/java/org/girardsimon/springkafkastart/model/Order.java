package org.girardsimon.springkafkastart.model;

import java.math.BigDecimal;
import java.util.UUID;

public record Order(UUID orderId, BigDecimal price) {
}
