package com.demien.eventStore

import org.scalatest.FunSuite

class InMemoryEventStoreTest extends FunSuite {

  case class TestEntity(val id: Int)

  val store = new InMemoryEventStore[TestEntity]

  test("testGenerateId") {
    assert(store.generateId() === 1)
    assert(store.generateId() === 2)


  }

  test("testSaveEvents: versions") {
    store.saveEvents(101, 10, Seq(TestEntity(1),TestEntity(2) )  )
    val vEvents = store.getEventsWithVersions(101)

    assert(vEvents.head._1.id === 1)
    assert(vEvents.head._2 === 11)

    assert(vEvents.last._1.id === 2)
    assert(vEvents.last._2 === 12)
  }

  test("testSaveEvents: StoreOptimisticLockingException") {
    val events = Seq(TestEntity(1),TestEntity(2) )
    store.saveEvents(102, 10,   events)

    intercept[StoreOptimisticLockingException]{
      store.saveEvents(102, 10,   events)
    }


  }


}
