package com.demien.eventBus

trait EventHandler[E] {

  def getEventNamesToSubscribe(): Seq[String]

  def onEvent(event: E): Unit

}
