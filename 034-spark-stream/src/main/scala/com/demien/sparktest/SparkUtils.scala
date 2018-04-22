package com.demien.sparktest

import org.apache.spark.{SparkConf, SparkContext}

//import org.apache.spark.sql.SparkSession

object SparkUtils {

  /*
  def getSpark = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .master("local[2]")
    .getOrCreate()

*/

  def getSparkContext(appName: String, master: String)  = new SparkContext( new SparkConf().setAppName(appName).setMaster(master) )
  def getSparkContext(appName: String): SparkContext  = getSparkContext(appName, "local[2]")
  def getSparkContext():SparkContext = getSparkContext("MySparkApp")



}
