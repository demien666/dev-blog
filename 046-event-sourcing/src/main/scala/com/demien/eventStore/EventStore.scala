package com.demien.eventStore

trait EventStore[T] {

  def saveEvents(entityId: Int, version: Int,  events: Seq[T]): Unit = ???

  def getEventsWithVersions(entityId: Int): Seq[(T,Int)] = ???

  def generateId(): Int = ???

}
