package com.demien.sparktest

// https://spark.apache.org/docs/2.3.0/rdd-programming-guide.html
object RddApp extends App {

  val sc = SparkUtils.getSparkContext()
  val file = sc.textFile("src/main/resources/sample.txt")
  val words = file.flatMap(l => l.split(" ")).filter(w => w.length > 1)
  val pairs = words.map(s => (s, 1)) // [the, of, the] => (the, 1) (of, 1) (the, 1)
  val counts = pairs.reduceByKey((a, b) => a + b) // (the, 1) (of, 1) (the, 1) => (the, 2) (of, 1)
  val countByWord = counts.map(p => p.swap) // (the, 2) (of, 1) => (2, the) (1, of)
  val countByWordSorted = countByWord.sortByKey(false)
  val top5 = countByWordSorted.take(5)

  top5.foreach(p => println(p))

}
