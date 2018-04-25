package com.demien.sparktest

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, State, StateSpec, StreamingContext}

object Streaming extends App {


  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(10))
  ssc.checkpoint("spark-checkpoint")

  val lines = ssc.textFileStream("data")
  val words = lines.flatMap(_.split(" "))
  val pairs = words.map(word => (word, 1))
  val wordCounts = pairs.reduceByKey(_ + _)


  def specFunc = (key: String, value: Option[Int], state: State[Map[String, Int]]) => {
    var newState = state.getOption().getOrElse(Map[String, Int]())
    var newValue = value.getOrElse(1)
    val currVal = newState.get(key).orElse(Some(0)).get
    newValue = newValue + currVal
    newState = newState - key
    newState = newState + (key -> newValue)
    state.update(newState)
    (key, newValue)
  }

  val spec = StateSpec.function(specFunc).timeout(Seconds(30))

  val wordsMapped = wordCounts.mapWithState(spec)

  // top 10
  wordsMapped.foreachRDD(rdd => {
    rdd.map(e => (e._2, e._1)).sortByKey(false).take(10).foreach(e => print(e._1, e._2))

  })

  ssc.start() // Start the computation
  ssc.awaitTermination() // Wait for the computation to terminate

}
