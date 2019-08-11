package com.demien.projection

trait EventDrivenRepository[T, E] {

  def getById(id: Int): T

  def applyEvent(id: Int, event: E): Unit


}
