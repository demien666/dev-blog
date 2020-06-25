package com.dmitry.revolute.repository

import com.dmitry.revolute.domain.Entity

trait Repository[T <: Entity] {

  def get(id: String):T
  def getFromEntity(entity: T):T = get(entity.id)
  def put(entity: T): Unit

}
