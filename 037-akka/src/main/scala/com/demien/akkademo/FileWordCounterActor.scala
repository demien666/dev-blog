package com.demien.akkademo

import akka.actor.{Actor, ActorRef, Props}

case class StartProcessFileMsg()

class FileLetterCounterActor(filename: String) extends Actor {

  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: ActorRef = null

  def receive = {
    case StartProcessFileMsg() => {
      fileSender = sender
      import scala.io.Source._
      fromFile(filename).getLines.foreach { line =>
        val stringCounterActor = context.actorOf(Props[StringCounterActor])
        stringCounterActor.tell(ProcessStringMsg(line), self)
        totalLines += 1
      }

    }
    case StringProcessedMsg(wordsCount) => {
      result += wordsCount
      linesProcessed += 1
      if (linesProcessed == totalLines) {
        fileSender.tell(result, self)
      }
    }
    case _ => println("message not recognized!")
  }
}