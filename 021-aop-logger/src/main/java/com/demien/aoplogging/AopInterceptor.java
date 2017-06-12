package com.demien.aoplogging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import java.util.Date;
import java.util.Map;

@Aspect
public class AopInterceptor {

    enum TIME_FORMAT {ms, sec, min};
    TIME_FORMAT currentTimeFormat=TIME_FORMAT.ms;


    public AopInterceptor() {}

    public AopInterceptor(Map<String, String> params) {
        String timeFormat=params.get("TIME_FORMAT");
        if (timeFormat!=null) {
            currentTimeFormat=TIME_FORMAT.valueOf(timeFormat);
        }
    }

    @Around("execution(* *(..))")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String className=joinPoint.getTarget().getClass().getName();
        String methodName=joinPoint.getSignature().getName();

        Long startTime=getCurrentTime();
        log("Started : ["+className+"] : " + methodName);
        joinPoint.proceed();
        Long endTime=getCurrentTime();

        log("Finished : [" + className + "] : " + methodName+" ExecutionTime is: "+getExecutionInterval(startTime)+currentTimeFormat);
    }

    private Long getCurrentTime() {
        return (new Date()).getTime();
    }

    private Long getExecutionInterval(Long startTime) {
        Long endTime=getCurrentTime();
        Long interval=endTime-startTime;
        switch (currentTimeFormat) {
            case min: interval=interval/60;
            case sec: interval=interval/1000;
            default: break;
        }
        return interval;
    }


    private void log(final String messageToLog)
    {
        // here have to be logging to some specific location
        System.out.println(messageToLog);
    }
}
