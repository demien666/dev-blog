package com.demien.ddd;

import com.demien.ddd.application.SpringApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.demien.ddd")
public class AppConfig {

    @Bean
    SpringApplicationContext getContext() {
        return new SpringApplicationContext();
    }


}
