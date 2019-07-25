package com.demien.eventBus

import com.demien.ddd.Event
import org.scalatest.FunSuite

class InMemoryEventBusTest extends FunSuite {

  val eventBus = new InMemoryEventBus[Event]()

  class TestHandler extends  EventHandler[Event] {
    var handled = Seq[Event]()
    override def onEvent(event: Event): Unit = handled = handled ++ Seq(event)
  }

  class TestEvent extends Event

  test("testRegisterEventHandler") {
    val handler1 = new TestHandler()
    val handler2 = new TestHandler()

    val testEvent = new TestEvent

    eventBus.registerEventHandler("TestEvent", handler1)
    eventBus.dispatch(testEvent)

    assert(handler1.handled.length === 1)
    assert(handler2.handled.length === 0)

  }

}
