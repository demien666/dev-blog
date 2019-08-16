package com.demien.projection

import org.scalatest.FunSuite

class InMemoryEventDrivenRepositoryTest extends FunSuite {

  case class Entity(value: Int)

  case class Event()

  val repo = new InMemoryEventDrivenRepository[Entity, Event](
    _ => Entity(0),
    (entity, _) => Entity(entity.value + 1)
  )

  test("main test") {
    val id = 42
    val event = Event()
    repo.applyEvent(id, event)
    repo.applyEvent(id, event)
    repo.applyEvent(id, event)

    assert(repo.getById(id).value == 3)

    repo.applyEvent(43, event)
    repo.applyEvent(44, event)

    assert(repo.find(e => e.value > 1) == Seq(Entity(3)))


  }

}
