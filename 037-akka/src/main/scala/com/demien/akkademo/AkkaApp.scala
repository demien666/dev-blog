package com.demien.akkademo

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts._

object AkkaApp extends App {

  implicit val ec = global


  val system = ActorSystem("System")
  val actor = system.actorOf(Props(new WordCounterActor(args(0))))
  implicit val timeout = Timeout(25 seconds)
  val future = actor ? StartProcessFileMsg()
  future.map { result =>
    println("Total number of words " + result)
    system.terminate()
  }


}
