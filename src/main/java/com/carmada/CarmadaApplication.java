package com.carmada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.carmada")
@EnableAutoConfiguration
public class CarmadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarmadaApplication.class, args);
	}

}
