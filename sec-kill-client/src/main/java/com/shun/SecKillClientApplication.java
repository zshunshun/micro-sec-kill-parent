package com.shun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.shun.dao")
public class SecKillClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecKillClientApplication.class, args);
    }

}
