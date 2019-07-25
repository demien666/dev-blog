package com.demien.eventBus

import com.demien.ddd.Event

trait EventBus[E] {

  def registerEventHandler(eventClassName: String, eventHandler: EventHandler[E]): Unit

  def dispatch(event: E): Unit

}
