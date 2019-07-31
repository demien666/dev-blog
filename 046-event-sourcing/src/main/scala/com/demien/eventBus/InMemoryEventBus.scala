package com.demien.eventBus

import java.util.concurrent.ConcurrentHashMap
import java.util.function.BiFunction

class InMemoryEventBus[E] extends EventBus[E] {

  val handlers = new ConcurrentHashMap[String, Seq[EventHandler[E]]]()

  val mergeFunction: BiFunction[Seq[EventHandler[E]], Seq[EventHandler[E]], Seq[EventHandler[E]]] =
    (_old: Seq[EventHandler[E]], _new: Seq[EventHandler[E]]) =>  _old ++ _new


  override def registerEventHandler(eventHandler: EventHandler[E]): Unit =
    eventHandler.getEventNamesToSubscribe().foreach(
      eventClassName => handlers.merge(eventClassName.replace("$", ""), Seq(eventHandler), mergeFunction)
    )

  override def dispatch(event: E): Unit =
    Option(handlers.get(event.getClass.getSimpleName))
      .getOrElse(Seq())
      .foreach(_.onEvent(event))

}
