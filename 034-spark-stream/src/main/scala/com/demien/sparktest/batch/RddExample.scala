package com.demien.sparktest.batch

import com.demien.sparktest.SparkUtils
import org.apache.spark.rdd.RDD

// https://spark.apache.org/docs/2.3.0/rdd-programming-guide.html
object RddExample extends App {

  val sc = SparkUtils.getSparkContext()
  val file = sc.textFile("src/main/resources/sample.txt")
  val words: RDD[String] = file.flatMap(l => l.split(" ")).filter(w => w.length > 1)
  val pairs: RDD[(String, Int)] = words.map(s => (s, 1)) // [the, of, the] => (the, 1) (of, 1) (the, 1)
  val counts: RDD[(String, Int)] = pairs.reduceByKey((a, b) => a + b) // (the, 1) (of, 1) (the, 1) => (the, 2) (of, 1)
  val countByWord: RDD[(Int, String)] = counts.map(p => p.swap) // (the, 2) (of, 1) => (2, the) (1, of)
  val countByWordSorted: RDD[(Int, String)] = countByWord.sortByKey(false)
  val top5 = countByWordSorted.take(5)

  top5.foreach(p => println(p))
}
