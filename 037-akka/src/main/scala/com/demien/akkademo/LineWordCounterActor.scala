package com.demien.akkademo

import akka.actor.Actor

case class CountWordsInLineMsg(line: String)

case class WordsInLineResultMsg(words: Integer)

class LineWordCounterActor extends Actor {
  def receive = {
    case CountWordsInLineMsg(string) => {
      val wordsInLine = string.split(" ").length
      sender.tell(WordsInLineResultMsg(wordsInLine), self)
    }
    case _ => println("Error: message not recognized")
  }
}