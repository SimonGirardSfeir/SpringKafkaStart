package org.girardsimon.springkafkastart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringKafkaStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaStartApplication.class, args);
    }
}
