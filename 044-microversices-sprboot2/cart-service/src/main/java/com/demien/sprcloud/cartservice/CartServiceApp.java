package com.demien.sprcloud.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients("com.demien.sprcloud.cartservice")
@SpringBootApplication
public class CartServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApp.class, args);
    }
}
