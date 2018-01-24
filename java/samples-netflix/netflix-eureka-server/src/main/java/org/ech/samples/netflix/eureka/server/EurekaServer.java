package org.ech.samples.netflix.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EurekaServer {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer.class, args);
	}
	
}