package com.testres.testrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TestrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestrestApplication.class, args);
	}

}
