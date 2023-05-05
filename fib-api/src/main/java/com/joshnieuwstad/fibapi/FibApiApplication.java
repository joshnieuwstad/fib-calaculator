package com.joshnieuwstad.fibapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.joshnieuwstad.fibapi"})
@SpringBootApplication
public class FibApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FibApiApplication.class, args);
	}



}
