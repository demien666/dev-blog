package com.demien.spock

import spock.lang.Specification;

class GroovyTest extends Specification {

    def "it should say Hi for regular name"() {
        given:
        def person=new TestJavaClass()

        when :
        def result=person.sayHi("Huan Sebastyan")

        then:
        result!=null
        result=="Hi, Huan Sebastyan"
    }

    def "it should thrown an error if name is null"() {
        given:
        def person=new TestJavaClass()

        when :
        def result=person.sayHi(null)

        then:
        thrown(IllegalArgumentException)
    }

    // mock - is a "empty" class: all methods are returning null.
    def "check method invocation count"() {
        given:
        TestJavaClass mock=Mock()
        AnotherJavaClass anotherJavaClass=new AnotherJavaClass(testJavaClass: mock)

        when:
        mock.sayHi("Test Name") // first invocation
        anotherJavaClass.introduceYourself() // second invocation

        then:
        2*mock.sayHi(_)
    }

    //  you have to re-define each method you want to use for mock
    def "Mocked class used in another class should return mocked value"() {
        setup :
        TestJavaClass mock=Mock()
        mock.sayHi(_)>>"EMPTY"
        AnotherJavaClass anotherJavaClass=new AnotherJavaClass(testJavaClass: mock)

        when:
        String result=anotherJavaClass.introduceYourself()

        then:
        result=="EMPTY"
    }

    def "Mocked class should return proper mocked history"() {
        setup :
        TestJavaClass mock=Mock()
        mock.getHistoryItems()>>["one", "two", "three"]
        AnotherJavaClass anotherJavaClass=new AnotherJavaClass(testJavaClass: mock)

        when:
        String result=anotherJavaClass.getHistory()

        then:
        result=="one,two,three"
    }

    // stubbed class - "empty" class like mock, but methods are not returning values at all
    // so, like in mocks, you have to re-define each method you want to use
    def "stubbed class used in another class"() {
        setup:
        TestJavaClass stub=Stub()

        stub.sayHi(_)>>{String name->
            "Hello, "+name
        }
        AnotherJavaClass anotherJavaClass=new AnotherJavaClass(testJavaClass: stub)

        when:
        String result=anotherJavaClass.introduceYourself()

        then:
        result=="Hello, I'm AnotherJavaClass"
    }

    // spy - is a "wrapper" - 100% working copy of class
    //  but you can re-define methods if you want
    def "spy test"() {
        setup:
        TestJavaClass spy=Spy()
        spy.checkName(null)>>{} // now calling this method with (null) will not cause exeption

        AnotherJavaClass anotherJavaClass=new AnotherJavaClass(testJavaClass: spy)

        when:
        String resultFromClass=anotherJavaClass.introduceYourself()
        String resultFromSpy=spy.sayHi(null)


        then:
        resultFromClass=="Hi, I'm AnotherJavaClass"
        resultFromSpy=="Hi, null" // exception was not thrown
    }

}