package com.demien.ddd

import com.demien.cqrs.Command
import com.demien.eventStore.EventStore
import org.scalatest.FunSuite

import scala.collection.mutable

class ServiceTest extends FunSuite {

  case class TestEntity(val value:Int)
  case class TestEvent(val newValue:Int) {}

  class TestAggregate extends Aggregate[TestEntity, TestEvent] {
    override def newInstance(): TestEntity = TestEntity(0)
    override def applyEvent(entity: TestEntity, event: TestEvent): TestEntity = TestEntity(event.newValue)
    override def processCommand(entity: TestEntity, command: Command): Seq[TestEvent] = Seq(TestEvent(10), TestEvent(20), TestEvent(30))
  }

  class TestEventStore extends EventStore[TestEvent] {
    var savedEvents = Seq[TestEvent]()
    override def getEventsWithVersions(entityId: Int): Seq[(TestEvent, Int)] =
      if (entityId == 0) Seq()
      else   Seq( (TestEvent(10), 1), (TestEvent(20), 2), (TestEvent(30), 3)     )

    override def saveEvents(entityId: Int, version: Int, events: Seq[TestEvent]): Unit = savedEvents = events
  }

  val testAggregate = new TestAggregate
  val testEventStore = new TestEventStore

  val service = new Service[TestEntity,TestEvent](testEventStore, testAggregate)

  test("testProcess") {
    service.process(100, new Command {})
    assert(testEventStore.savedEvents === Seq(TestEvent(10), TestEvent(20), TestEvent(30)))



  }


  test("testGetEntityAndVersion") {
    val ev0 = service.getEntityAndVersion(0)
    assert(ev0._1.value === 0)
    assert(ev0._2 === 0)

    val ev1 = service.getEntityAndVersion(1)
    assert(ev1._1.value === 30)
    assert(ev1._2 === 3)

  }


}
