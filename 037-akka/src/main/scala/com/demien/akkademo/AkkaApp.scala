package com.demien.akkademo

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.ask
import akka.dispatch.ExecutionContexts.global

object AkkaApp extends App {

  val system = ActorSystem("System")
  val wcActor = system.actorOf(Props(new FileLetterCounterActor(args(0))))
  val wcActorAskWithTimeout = wcActor.ask(_: Any)(Timeout(10 seconds))

  val askingFuture = wcActorAskWithTimeout(StartProcessFileMsg())
  val askingFutureGlobalContextMap = askingFuture.map(_: Any => Any)(global())

  askingFutureGlobalContextMap(result => {
    println("Total number of words " + result)
    system.terminate()
    result
  })


}
