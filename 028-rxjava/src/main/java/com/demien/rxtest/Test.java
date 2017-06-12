package com.demien.rxtest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

/**
 * Created by dmytro.kovalskyi on 3/20/2017.
 */
public class Test {

    public static void main(String[] args)
    {
        String s = "test[123].name";
        System.out.println(s.replaceAll("All\\[[0-9]+\\]",""));

    }


    @FunctionalInterface
    public interface MyCommand0Args {
        void execute();
    }

    @FunctionalInterface
    public interface MyCommand1Arg {
        void execute(String arg1);
    }

    @FunctionalInterface
    public interface MyCommand2Args {
        void execute(String arg1, String arg2);
    }

    public void runLambda0Args(MyCommand0Args command) {
        command.execute();
    }

    public void runLambda1Arg(MyCommand1Arg command, String arg1) {
        command.execute(arg1);
    }

    public void runLambda2Args(MyCommand2Args command, String arg1, String arg2) {
        command.execute(arg1, arg2);
    }

    public void runLambdaTest() {

        runLambda0Args(() -> System.out.println("Hello World"));
        runLambda1Arg(name -> System.out.println("Hello " + name), "Joe");
        runLambda2Args((greetingString, name) -> {
                    String result = greetingString + ", " + name + "!!!";
                    System.out.println(result);
                }
                , "Hi", "Black");
    }

    public boolean numberCheck(Predicate<Integer> checker, int number) {
        return checker.test(number);
    }

    public boolean isPositive(int number) {
        return numberCheck(n -> n > 0, number);
    }

    public boolean isNegative(int number) {
        return numberCheck(n -> n < 0, number);
    }


    @FunctionalInterface
    public interface BinaryOperation<T> {
        T calculate(T x, T y);
    }

    public int intOperation(BinaryOperation<Integer> operation, int x, int y) {
        return operation.calculate(x, y);
    }

    public int intAdd(int x, int y) {
        return intOperation((op1, op2) -> op1 + op2, x, y);
    }

    public int intSub(int x, int y) {
        return intOperation((op1, op2) -> op1 - op2, x, y);
    }

    public void test() {
        Supplier<String> stringSupplier = () -> "hello world";
        Consumer<String> stringConsumer = (name) -> System.out.println("Hello, " + name);
        BiConsumer<String, Integer> stringIntegerBiConsumer = (name, age) -> System.out.println(name + " is " + age + " years old");
        Predicate<String> stringPredicate = (name) -> name == null;
        BiPredicate<String, Integer> stringIntegerBiPredicate = (name, age) -> name != null && name.length() > 0 && age != null && age > 0;
        Function<String, String> stringFunction = (name) -> "Hello, " + name;
        BiFunction<String, Integer, String> stringIntegerStringBiFunction = (name, age) -> name + " is " + age + " years old";
        UnaryOperator<String> stringUnaryOperator = (name) -> name.toUpperCase();
        BinaryOperation<String> stringBinaryOperation = (name, surname) -> name + " " + surname;



        Consumer<String> print = System.out::println;
        print.accept("hello");

    }

    public void test2() {
        Supplier<String> stringSupplier = new Date()::toString;
        Consumer<String> stringConsumer = System.out::println;
        Predicate<List<String>> stringPredicate = List::isEmpty;

        BiPredicate<String, Integer> stringIntegerBiPredicate = (name, age) -> name != null && name.length() > 0 && age != null && age > 0;
        Function<String, String> stringFunction = (name) -> "Hello, " + name;

        BiFunction<String, Integer, String> stringIntegerStringBiFunction = (name, age) -> name + " is " + age + " years old";

        UnaryOperator<Integer> stringUnaryOperator = Object::hashCode;

        BinaryOperation<String> stringBinaryOperation = (name, surname) -> name + " " + surname;



        Consumer<String> print = System.out::println;
        print.accept("hello");

    }

    public static class MyDBEntity {
        Long id;
        String value;

        public MyDBEntity(Long id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    public Optional<List<MyDBEntity>> optionalTest(String userId) {
        if (userId.equals("ADMIN")) {
            List<MyDBEntity> result = new ArrayList<>();
            result.add(new MyDBEntity(1L, "My Admin"));
            return Optional.of(result);
        } else {
            return Optional.empty();
        }

    }






}
