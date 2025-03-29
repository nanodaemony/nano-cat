package com.nano.cat;

import com.nano.common.util.IdUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatApplication.class, args);
        IdUtils.genId();
        System.out.println("Swagger: " + "https://elegantfish.online/api/doc.html#/home");
    }

}
