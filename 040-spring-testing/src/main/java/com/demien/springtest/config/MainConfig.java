package com.demien.springtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =  {"com.demien.springtest.service"})
public class MainConfig {

}
