package com.niu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan({"com.niu.entity"})
@EnableJpaRepositories(basePackages = "com.niu.repository")
@EnableJpaAuditing
@EnableTransactionManagement
public class CollegeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeApplication.class, args);
    }
}
