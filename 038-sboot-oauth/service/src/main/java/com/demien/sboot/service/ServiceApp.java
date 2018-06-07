package com.demien.sboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
@EnableResourceServer
public class ServiceApp {

    @Autowired
    private ResourceServerProperties sso;

    @Bean
    public ResourceServerTokenServices myUserInfoTokenServices() {
        return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
    }

    @RequestMapping("/mydata")
    @PreAuthorize("#oauth2.hasScope('data_read') and hasAuthority('ROLE_ADMIN')")
    public ArrayList<MyData> getTollData() {

        ArrayList<MyData> result = new ArrayList<MyData>();
        result.add(new MyData(1L, "one"));
        result.add(new MyData(2L, "two"));
        result.add(new MyData(3L, "three"));

        return result;
    }


    public class MyData {

        public final Long myId;
        public final String myValue;

        public MyData(Long myId, String myValue) {
            this.myId = myId;
            this.myValue = myValue;
        }

        public Long getMyId() {
            return myId;
        }

        public String getMyValue() {
            return myValue;
        }
    }


}
