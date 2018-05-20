package com.demien.akkademo

import akka.actor.{Actor, ActorRef, Props}

case class StartProcessFileMsg()

class FileWordCounterActor(filename: String) extends Actor {

  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: ActorRef = null

  def receive = {
    case StartProcessFileMsg() => {
      fileSender = sender
      import scala.io.Source._
      fromFile(filename).getLines.foreach { line =>
        val lineWordCounterActor = context.actorOf(Props[LineWordCounterActor])
        lineWordCounterActor.tell(CountWordsInLineMsg(line), self)
        totalLines += 1
      }

    }
    case WordsInLineResultMsg(wordsCount) => {
      result += wordsCount
      linesProcessed += 1
      if (linesProcessed == totalLines) {
        fileSender.tell(result, self)
      }
    }
    case _ => println("message not recognized!")
  }
}