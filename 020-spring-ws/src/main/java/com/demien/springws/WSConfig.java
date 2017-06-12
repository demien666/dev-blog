package com.demien.springws;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@Configuration
@EnableWs
public class WSConfig {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "CategoryService")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema CategoryOperations) {
        DefaultWsdl11Definition result = new DefaultWsdl11Definition();
        result.setPortTypeName("CategoryWS");
        result.setLocationUri("/ws");
        result.setTargetNamespace("http://com/demien/springws/domain/operation");

        result.setSchema(CategoryOperations);
        return result;
    }

    @Bean
    public XsdSchema Category() {
        return new SimpleXsdSchema(new ClassPathResource("Category.xsd"));
    }

    @Bean
    public XsdSchema CategoryOperations() {
        return new SimpleXsdSchema(new ClassPathResource("CategoryOperations.xsd"));
    }

}
