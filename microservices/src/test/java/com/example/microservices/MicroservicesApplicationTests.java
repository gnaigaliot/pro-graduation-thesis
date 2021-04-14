package com.example.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages={"com/example/demo"})
public class MicroservicesApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesApplicationTests.class, args);
    }
}

