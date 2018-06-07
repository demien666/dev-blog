package com.demien.sboot.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
@RestController
public class ClientApp {

    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

    @RequestMapping("/autologin")
    public void autoLogin() {
        // TODO Auto-generated method stub
        System.out.println("starting");
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetails.setAccessTokenUri("http://localhost:9000/services/oauth/token");
        resourceDetails.setScope(Arrays.asList("data_select"));
        resourceDetails.setClientId("myClientId");
        resourceDetails.setClientSecret("myClientSecret");
        resourceDetails.setUsername("joe");
        resourceDetails.setPassword("black");

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails);
        String token = restTemplate.getAccessToken().getValue();
        System.out.println(token);

        String s = restTemplate.getForObject("http://localhost:9001/services/mydata", String.class);
        System.out.println("Result:" + s);
    }


}
