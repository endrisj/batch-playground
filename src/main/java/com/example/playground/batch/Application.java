package com.example.playground.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        System.exit(SpringApplication.exit(
            SpringApplication.run(BatchConfiguration.class, args)
        ));
    }
}
