package com.shun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SecKillClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillClientApplication.class, args);
    }

}
