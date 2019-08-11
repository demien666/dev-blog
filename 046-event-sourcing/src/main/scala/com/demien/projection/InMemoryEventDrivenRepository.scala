package com.demien.projection

import java.util.concurrent.ConcurrentHashMap

class InMemoryEventDrivenRepository[T, E]
(val newInstance: Unit => T, val applyEvent: (T, E) => T) extends EventDrivenRepository[T, E] {

  val storage = new ConcurrentHashMap[Int, T]()


  override def getById(id: Int): T = storage.get(id)

  override def applyEvent(id: Int, event: E): Unit = {
    val entity = if (storage.get(id) == null) newInstance() else storage.get(id)
    storage.put(id, applyEvent(entity, event))

  }


}
