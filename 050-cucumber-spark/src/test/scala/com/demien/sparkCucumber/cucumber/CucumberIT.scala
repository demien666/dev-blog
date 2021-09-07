package com.demien.sparkCucumber.cucumber

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array(
    //"classpath:features/InputToOutput.feature"
  ),
  monochrome = true,

  glue = Array("classpath:com/demien/sparkCucumber/cucumber"),
  plugin = Array(
    "pretty",
    "html:target/cucumber/html",
    "json:target/cucumber/json"//,
    //"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
  )
)
class CucumberIT {

}
