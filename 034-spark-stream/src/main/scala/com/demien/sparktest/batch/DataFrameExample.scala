package com.demien.sparktest.batch

// https://spark.apache.org/docs/latest/sql-programming-guide.html

import org.apache.spark.sql.SparkSession

object DataFrameExample extends App {

  val spark = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .config("spark.master", "local")
    .getOrCreate()

  // For implicit conversions like converting RDDs to DataFrames
  import spark.implicits._


  val df = spark.read.json("src/main/resources/people.json")
  df.printSchema()
  df.createOrReplaceTempView("people")
  val sqlDF = spark.sql("SELECT * FROM people where email like '%net%' ")
  sqlDF.show()

  case class Person(name: String, email: String="empty", city: String, mac: String, timestamp: String, creditcard: String)

  val peopleDS = spark.read.json("src/main/resources/people.json").as[Person]
  val filteredDS = peopleDS.filter(p => p.email != null && p.email.contains("net"))
  filteredDS.show()


}
