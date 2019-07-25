package com.demien.eventStore

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import java.util.function.BiFunction

class InMemoryEventStore[T] extends EventStore[T] {

  type VersionedEvents = Seq[(T,Int)]

  val index = new AtomicInteger(0)

  val storage = new ConcurrentHashMap[Int, VersionedEvents]()

  val mergeFunction: BiFunction[VersionedEvents, VersionedEvents, VersionedEvents] =
    (_old: VersionedEvents, _new: VersionedEvents) =>  _old ++ _new

  override def saveEvents(entityId: Int, lastVersion: Int, events: Seq[T]): Unit = {
    events.foreach(e => println("entityId:" + entityId + " StoringEvent:" + e))
    val oldEvents = storage.get(entityId)
    if (oldEvents != null && oldEvents.last._2 > lastVersion) throw new StoreOptimisticLockingException
    var eachVersion = lastVersion
    val versionedEvents = events.map(event => {
      eachVersion = eachVersion + 1
      (event, eachVersion)
    })
      storage.merge(entityId, versionedEvents, mergeFunction)
  }



  def getEvents(entityId: Int): Seq[T] =
    if (storage.get(entityId) == null) Seq()
    else storage.get(entityId).map(eventAndVersion => eventAndVersion._1)

  override def generateId(): Int = index.incrementAndGet()

  override def getEventsWithVersions(entityId: Int): Seq[(T, Int)] =
    if (storage.get(entityId) == null) Seq() else storage.get(entityId)


}
