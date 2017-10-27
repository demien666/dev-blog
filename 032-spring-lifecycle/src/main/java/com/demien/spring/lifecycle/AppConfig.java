package com.demien.spring.lifecycle;

import com.demien.spring.lifecycle.annotations.GeneratedName;
import com.demien.spring.lifecycle.annotations.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

@Configuration
@ComponentScan(basePackages = "com.demien.spring.lifecycle")
public class AppConfig {

    private Random random = new Random();
    final String abc = "abcdefghijklmnopqrstuwqxyz";

    public String generateName(int min, int max) {
        StringBuilder result = new StringBuilder();
        int length = min + random.nextInt(max - min);
        IntStream.rangeClosed(1, length).forEach(
                (e) -> {
                    char ch = abc.charAt(random.nextInt(abc.length()));
                    result.append(e==1? Character.toUpperCase(ch): ch);
                }
        );
        return result.toString();
    }

    /*
    @Bean
    BeanPostProcessor commonAnnotationBeanPostProcessor() {
        return new CommonAnnotationBeanPostProcessor();
    }
    */


    @Bean
    Messenger messenger() {
        SimpleMessenger messenger = new SimpleMessenger();
        messenger.setMessageText("Hello");
        return messenger;
    }

    @Bean
    BeanPostProcessor randomIntPostProcessor() {

        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field field : fields) {
                    GeneratedName annotation = field.getAnnotation(GeneratedName.class);
                    if (annotation != null) {
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, bean, generateName(annotation.minLength(), annotation.maxLength()));
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
                if (beanClass != null) {
                    return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
                            long before = System.nanoTime();

                            Object retval = method.invoke(bean, objects);
                            if (profilerSettings.isEnabled()) {
                                System.out.println("exec time:" + (System.nanoTime() - before));
                            }
                            return retval;
                        }
                    });
                }
                return null;
            }
        };
    }


}
