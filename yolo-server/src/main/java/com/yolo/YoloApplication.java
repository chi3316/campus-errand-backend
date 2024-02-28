package com.yolo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class YoloApplication {
    public static void main(String[] args) {
        SpringApplication.run(YoloApplication.class, args);
    }
}
