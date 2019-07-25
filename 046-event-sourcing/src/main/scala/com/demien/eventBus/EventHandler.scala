package com.demien.eventBus

import com.demien.ddd.Event

trait EventHandler[E] {

  def onEvent(event: E): Unit

}
