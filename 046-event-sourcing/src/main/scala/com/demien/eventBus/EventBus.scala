package com.demien.eventBus

trait EventBus[E] {

  def registerEventHandler(eventHandler: EventHandler[E]): Unit

  def dispatch(event: E): Unit

}
