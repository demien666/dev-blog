package com.demien.sparktest

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object SparkUtils {

  val DEF_APP_NAME = "MySparkApp"
  val DEF_MASTER = "local[2]"

  //The SparkContext object - connection to a Spark execution environment and created RDDs
  def getSparkContext(appName: String, master: String): SparkContext = new SparkContext(new SparkConf().setAppName(appName).setMaster(master))

  def getSparkContext(): SparkContext = getSparkContext(DEF_APP_NAME, DEF_MASTER)

  //The SparkSession - connection to dataframes and SQLs
  def getSparkSession(appName: String, master: String): SparkSession = SparkSession
    .builder()
    .appName(appName)
    .master(master)
    .getOrCreate()

  def getSparkSession(): SparkSession = getSparkSession(DEF_APP_NAME, DEF_MASTER)


}
