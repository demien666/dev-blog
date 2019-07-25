package com.demien.eventBus

import java.util.concurrent.ConcurrentHashMap
import java.util.function.BiFunction

import com.demien.ddd.Event

class InMemoryEventBus[E] extends EventBus[E] {

  val handlers = new ConcurrentHashMap[String, Seq[EventHandler[E]]]()

  val mergeFunction: BiFunction[Seq[EventHandler[E]], Seq[EventHandler[E]], Seq[EventHandler[E]]] =
    (_old: Seq[EventHandler[E]], _new: Seq[EventHandler[E]]) =>  _old ++ _new


  override def registerEventHandler(eventClassName: String, eventHandler: EventHandler[E]): Unit =
    handlers.merge(eventClassName, Seq(eventHandler), mergeFunction)


  override def dispatch(event: E): Unit =
    Option(handlers.get(event.getClass.getSimpleName))
      .getOrElse(Seq())
      .foreach(_.onEvent(event))

}
