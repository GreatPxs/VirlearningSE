package com.example.virlearning;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.example.virlearning.dao")

@SpringBootApplication

public class VirlearningApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirlearningApplication.class, args);
    }

}
