package ru.progmatik.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class FiasLoaderMainClass {
    private static Logger log = LoggerFactory.getLogger(FiasLoaderMainClass.class);
    public static void main(String[] args) {
        SpringApplication.run(FiasLoaderMainClass.class);
    }
}
