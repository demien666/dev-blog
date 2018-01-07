package com.demien.es.system;

import com.demien.es.domain.client.ClientFinder;
import com.demien.es.domain.loan.LoanFinder;

import java.util.HashMap;
import java.util.Map;

public class SpringContext /*implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONTEXT = applicationContext;
    }

    public static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }

    public static Object getBean(Class<?> clazz) {
        return CONTEXT.getBean(clazz);
    }
    */ {
    public final static EventBus eventBus = new EventBus();
    public final static LoanFinder LOAN_FINDER = new LoanFinder(eventBus);
    public final static ClientFinder CLIENT_FINDER = new ClientFinder(eventBus);
    public static Map<Class<?>, Object> CONTEXT = new HashMap<>();

    static {
        CONTEXT.put(LoanFinder.class, LOAN_FINDER);
        CONTEXT.put(CLIENT_FINDER.getClass(), CLIENT_FINDER);
        CONTEXT.put(EventBus.class, eventBus);
    }

    public static Object getBean(Class<?> clazz) {
        //return CONTEXT.getBean(clazz);
        return CONTEXT.get(clazz);
    }


}
