package com.demien.sparkCucumber.cucumber

import org.apache.log4j.Logger
import org.apache.spark.sql.DataFrame

object SparkCompareUtils {
  val LOG = Logger.getLogger(this.getClass)

  private def checkDiffUsingExcept(df1: DataFrame, df2: DataFrame, msg: String) = {
    val diff = df1.except(df2)
    val diffCount = diff.count()
    if (diffCount > 0) {
      LOG.error("Comparing:")
      df1.show(10, false)
      LOG.error("VS:")
      df2.show(10, false)
      LOG.error("DIFF:")
      diff.show(10, false)
      throw new Exception(s"$msg. Diff count: $diffCount ")
    }
  }

  def compareEqual(actual: DataFrame, expected: DataFrame): Unit = {
    val actualCount = actual.count()
    val expectedCount = expected.count()

    if (actualCount != expectedCount) {
      throw new Exception(s"Actual count=$actualCount but Expected count=$expectedCount ")
    }

    val expectedColumns = expected.columns
    val actualWithExpectedColumns = actual.selectExpr(expectedColumns: _*)

    checkDiffUsingExcept(actualWithExpectedColumns, expected, "Some records from Actual are not present in Expected")
    checkDiffUsingExcept(expected, actualWithExpectedColumns, "Some records from Expected are not present in Actual")
  }

}
