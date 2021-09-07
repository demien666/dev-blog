package com.demien.sparkCucumber.cucumber

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

object CucumberUtils {

  def dataTableToSeq(sparkSession: SparkSession, dataTable: io.cucumber.datatable.DataTable): DataFrame = {
    val size = dataTable.asLists().size()
    val header = dataTable.row(0)
    val structFields = header.toArray.map(col => StructField(col.toString, StringType, true) )
    val columnsCount = header.size()

    val rows = new Array[Row](size -1)

    for (i <- 1 until size) {
      val dataRow = dataTable.row(i)
      val scalaArray = new Array[String](columnsCount)

      for (j <- 0 until columnsCount) {
        scalaArray(j) = dataRow.get(j)
      }
      rows(i-1) = Row.fromSeq(scalaArray.toSeq)
    }

    val rdd: RDD[Row] = sparkSession.sparkContext.parallelize(rows)

    sparkSession.createDataFrame(rdd, StructType(structFields))
  }

}
