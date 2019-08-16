package com.demien.projection

trait EventDrivenRepository[T, E] {

  def getById(id: Int): T

  def find(predicate: T => Boolean): Seq[T]

  def applyEvent(id: Int, event: E): Unit


}
