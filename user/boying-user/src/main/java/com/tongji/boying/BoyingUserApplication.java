package com.tongji.boying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@SpringBootApplication(scanBasePackages = "com.tongji.boying")
public class BoyingUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoyingUserApplication.class, args);
    }
}
