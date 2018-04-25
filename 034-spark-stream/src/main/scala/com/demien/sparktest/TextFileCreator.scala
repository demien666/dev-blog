package com.demien.sparktest

import java.io.FileWriter
import java.util.Date

import scala.io.Source
import scala.util.Random

object TextFileCreator extends App {

  val listOfLines = Source.fromFile("src/main/resources/sample.txt").getLines.toList
  val rnd = new Random()

  while (true) {

    val fileName = new Date().getTime
    val fullFileName = "data/" + fileName + ".txt"
    val fw = new FileWriter(fullFileName, true)
    println("writing to " + fullFileName)

    val linesCount = rnd.nextInt(20) + 5
    for (i <- 1 to linesCount) fw.write(listOfLines(rnd.nextInt(100)) + "\n")

    fw.close()
    Thread.sleep(10000)

  }

}
