package com.demien.sboot.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.Arrays;

@SpringBootApplication
public class CommandLineApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApp.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
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
