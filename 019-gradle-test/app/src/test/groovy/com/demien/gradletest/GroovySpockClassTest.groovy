package com.demien.gradletest

import spock.lang.Specification


class GroovySpockClassTest extends Specification {

    def "Just say hello"() {
        System.out.println("Hello from groovy JUnit test class")
    }

    def "Unit test with mocked method, should return HOW ARE YOU, JOE"() {
        setup :
        TestProcessor mock=Mock()
        mock.getGreetingWord()>>"How are you"
        TestClass testClass=new TestClass(testProcessor: mock)

        when:
        String result=testClass.greeting("Joe")

        then:
        result=="How are you, Joe"
    }
}
