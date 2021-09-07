package com.demien.sparkCucumber

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object AbstractSparkTest {
  private val conf = new SparkConf()
    .setMaster("local[2]")
    .setAppName("spark-test")
    .set("spark.sql.shuffle.partitions", "4")
    .set("spark.testing", "true")

  private val  spark_ = SparkSession.builder().config(conf).getOrCreate()

//  val spark_ = SparkSession
//    .builder()
//    .appName("Spark SQL basic example")
//    .config("spark.some.config.option", "some-value")
//    .getOrCreate()
}


class AbstractSparkTest  {
  val sparkSession: SparkSession = AbstractSparkTest.spark_

}
