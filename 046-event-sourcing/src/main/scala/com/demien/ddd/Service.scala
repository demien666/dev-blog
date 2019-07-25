package com.demien.ddd

import com.demien.cqrs.Command
import com.demien.eventBus.EventBus
import com.demien.eventStore.EventStore

class Service[T,E](eventStore: EventStore[E], aggregate: Aggregate[T,E], eventBus: Option[EventBus[E]] = Option.empty) {

  def getEntityAndVersion(entityId: Int): (T,Int) = {
    var entity = aggregate.newInstance()
    val eventsAndVersions = eventStore.getEventsWithVersions(entityId)

    if (eventsAndVersions.isEmpty) return (entity, 0)

    eventsAndVersions.foreach(eventAndVersion => entity = aggregate.applyEvent(entity, eventAndVersion._1))

    (entity, eventsAndVersions.last._2)
  }


  def getEntity(entityId: Int): T = {
    getEntityAndVersion(entityId)._1
  }

  def process(command: Command): Unit = {
    val (entity, lastVersion) = this.getEntityAndVersion(command.aggregateId)
    val events = aggregate.processCommand(entity, command)
    eventStore.saveEvents(command.aggregateId, lastVersion + 1, events)
    if (eventBus.nonEmpty) events.foreach(eventBus.get.dispatch(_))
  }



}
