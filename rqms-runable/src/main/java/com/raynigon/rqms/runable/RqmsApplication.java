package com.raynigon.rqms.runable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.raynigon.rqms")
public class RqmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RqmsApplication.class, args);
	}

}
