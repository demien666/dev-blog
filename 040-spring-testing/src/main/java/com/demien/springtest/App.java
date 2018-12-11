package com.demien.springtest;

import com.demien.springtest.config.MainConfig;
import com.demien.springtest.domain.User;
import com.demien.springtest.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class App {
    public static void main(String[] args) {
        final User dummyUser = new User();
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        PermissionService permissionService =  context.getBean(PermissionService.class);
        log.info("Permission result: for [admin] group: " +  permissionService.hasAccess(dummyUser, "admin"));
        log.info("Permission result: for [first] group: " +  permissionService.hasAccess(dummyUser, "first"));
    }
}
