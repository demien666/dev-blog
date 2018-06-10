package com.demien.sboot.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@EnableOAuth2Sso
public class ClientController extends WebSecurityConfigurerAdapter {

    @Autowired
    private OAuth2ClientContext clientContext;

    @Autowired
    private OAuth2RestTemplate oauth2RestTemplate;

    @RequestMapping("/")
    public String loadHome() {
        return "Hello world!";

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/login**").permitAll().anyRequest().authenticated();
    }

    @RequestMapping("callservice")
    public String callService() {
        OAuth2AccessToken token = clientContext.getAccessToken();
        System.out.println("Token:" + token);

        ResponseEntity<ArrayList<MyData>> response =
                oauth2RestTemplate.exchange("http://localhost:9001/services/mydata", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<MyData>>() {
                });

        return response.getBody().toString();


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
