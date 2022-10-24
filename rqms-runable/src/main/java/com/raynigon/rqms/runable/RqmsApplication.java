package com.raynigon.rqms.runable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.raynigon.rqms")
@EnableJpaRepositories(basePackages = "com.raynigon.rqms")
@SpringBootApplication(scanBasePackages = "com.raynigon.rqms")
public class RqmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RqmsApplication.class, args);
    }

}
