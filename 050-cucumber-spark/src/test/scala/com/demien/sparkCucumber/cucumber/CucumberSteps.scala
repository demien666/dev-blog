package com.demien.sparkCucumber.cucumber

import com.demien.sparkCucumber.AbstractSparkTest
import com.demien.sparkCucumber.model.InputData
import com.demien.sparkCucumber.processing.{DataFrameMapper, InputToOutputMapper}
import io.cucumber.scala.{EN, ScalaDsl}
import org.apache.log4j.Logger
import org.apache.spark.sql.DataFrame


class CucumberSteps extends AbstractSparkTest with  ScalaDsl with EN    {

  val LOG = Logger.getLogger(this.getClass)

  var inputData: DataFrame = null
  var outputData: DataFrame = null

  Given("""input data:""") { (dataTable: io.cucumber.datatable.DataTable) => {
    val df =  CucumberUtils.dataTableToSeq(sparkSession, dataTable)
    //df.show()
    inputData = df
    outputData = null
  }

  }
  When("""date were processed by {string} at {string}""") { (mapperName: String, processingDate: String) =>
    LOG.info(s"Data processing by $mapperName  at $processingDate ")
    val mapper: DataFrameMapper = DataFrameMapper.getByName(mapperName)
    outputData = mapper.mapDf(inputData, processingDate)
    outputData.show(10, false)
  }

  Then("""output should be:""") { (dataTable: io.cucumber.datatable.DataTable) =>
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.scala.PendingException()
    val expected = CucumberUtils.dataTableToSeq(sparkSession, dataTable)
    SparkCompareUtils.compareEqual(outputData, expected)
  }


}
