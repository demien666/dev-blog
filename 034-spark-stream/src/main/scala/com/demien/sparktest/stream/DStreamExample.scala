package com.demien.sparktest.stream

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, State, StateSpec, StreamingContext}

object DStreamExample extends App {


  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  val ssc = new StreamingContext(conf, Seconds(10))
  ssc.checkpoint("spark-checkpoint")

  val lines = ssc.textFileStream("data")
  val words = lines.flatMap(_.split(" "))
  val pairs = words.map(word => (word, 1))
  val wordCounts = pairs.reduceByKey(_ + _)


  def specFunc = (key: String, value: Option[Int], state: State[Int]) => {
    var newState = state.getOption().getOrElse(0)
    var newValue = value.getOrElse(1)
    newState = newState + newValue
    state.update(newState)
    (key, newValue)
  }

  val spec = StateSpec.function(specFunc).timeout(Seconds(30))

  val wordsMapped = wordCounts.mapWithState(spec)

  // top 10
  wordsMapped.stateSnapshots().foreachRDD(rdd => {
    rdd.map(e => (e._2, e._1)).sortByKey(false).take(10).foreach(e => print(e._1, e._2))

  })

  ssc.start() // Start the computation
  ssc.awaitTermination() // Wait for the computation to terminate

}
