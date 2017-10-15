package com.demien.spring.lifecycle;

import com.demien.spring.lifecycle.annotations.Profiling;
import com.demien.spring.lifecycle.annotations.RandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Configuration
@ComponentScan(basePackages = "com.demien.spring.lifecycle")
public class AppConfig {

    private Random random = new Random();

    @Bean
    Messenger messenger() {
        SimpleMessanger messenger = new SimpleMessanger();
        messenger.setMessageText("Hello from bean method");
        return messenger;
    }

    @Bean
    BeanPostProcessor randomIntPostProcessor() {

        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field field:fields) {
                    RandomInt randomInt = field.getAnnotation(RandomInt.class);
                    if (randomInt !=null) {
                        int rand = randomInt.min()+random.nextInt(randomInt.max()- randomInt.min());
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, bean, rand);
                    }
                }
                return bean;
            }

        };
    }

    @Bean
    BeanPostProcessor profilingPostProcessor() throws Exception {
        Map<String, Class> map = new HashMap<>();
        ProfilerSettings profilerSettings = new ProfilerSettings();
        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
        beanServer.registerMBean(profilerSettings, new ObjectName("Profiling", "name", "settings"));


        return new BeanPostProcessor() {

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                Class<?> beanClass = bean.getClass();
                if (beanClass.isAnnotationPresent(Profiling.class)) {
                    map.put(beanName, beanClass);
                }
                return null;
            }

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                Class beanClass = map.get(beanName);
                if (beanClass!=null) {
                    return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                        @Override
                        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                            long before  = System.nanoTime();

                            method.invoke(o, objects);
                            if (profilerSettings.isEnabled()) {
                                System.out.println("exec time:"+(System.nanoTime()-before));
                            }
                            return null;
                        }
                    });
                }
                return null;
            }
        };
    }


}
