package com.demien.gradletest

import spock.lang.Specification

class IntegrationTest extends Specification {


    def "Main integration test with REAL HELLO PROCESSOR should return HELLO, JOE"() {

        setup :
        TestProcessor processor=new TestHelloProcessor()
        TestClass testClass=new TestClass(testProcessor: processor)

        when:
        String result=testClass.greeting("Joe")

        then:
        result=="Hello, Joe"
    }

    def "Main integration test with REAL HI PROCESSOR should return HI, JOE"() {

        setup :
        TestProcessor processor=new TestHiProcessor()
        TestClass testClass=new TestClass(testProcessor: processor)

        when:
        String result=testClass.greeting("Joe")

        then:
        result=="Hi, Joe"
    }


}
