package com.demien.jbehave.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public class MySteps {

    Integer TEST_NUMBER=123;

    List<Integer> list;

    public void addTestNumber() {
        list.add(TEST_NUMBER);
    }

    @Given("an empty list")
    public void givenEmptyList() {
        list=new ArrayList<Integer>();
    }

    @When("I add number 123")
    public void addNumber() {
        addTestNumber();
    }

    @Then("size becomes 1 and the list contains 123")
    public void thenSizeBecomes1AndTheListContains123() {
        Assert.assertEquals(1,list.size());
        Assert.assertTrue(list.contains(123));
    }

    @Given("a no-empty list")
    public void givenANoemptyList() {
        list=new ArrayList<Integer>();
        list.add(1);
    }

    @When("I remove number 123")
     public void whenIRemoveNumber123() {
        list.remove(TEST_NUMBER);
    }

    @Then("size becomes 0")
     public void thenSizeBecomes0() {
        Assert.assertTrue(list.size()==0);
    }
    
}
