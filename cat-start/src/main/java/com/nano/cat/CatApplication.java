package com.nano.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatApplication.class, args);
        System.out.println("Swagger: " + "http://localhost:8080/doc.html#/home");
    }

}
