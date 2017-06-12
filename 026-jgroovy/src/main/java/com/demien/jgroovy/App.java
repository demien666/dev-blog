package com.demien.jgroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmytro.kovalskyi on 10/6/2016.
 */
public class App {

    public static void main(String[] args) {
        Map<String, Object> groovyContext=new HashMap();

        groovyContext.put("NAME", "Huan Sebastyan");

        Binding binding=new Binding();

        binding.setVariable("context", groovyContext);

        GroovyShell shell = new GroovyShell(binding);
        //String script = "return 'hello '+context.get(\"NAME\") ";
        String script = "context.put(\"RESULT\",\"TEST DRIVE\");   return context; ";

        Object result = shell.evaluate(script);
        System.out.println(result);
    }

}
