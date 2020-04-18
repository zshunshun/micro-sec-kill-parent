package com.shun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@MapperScan("com.shun.dao")
public class SecKillClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillClientApplication.class, args);
    }

}
