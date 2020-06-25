package com.dmitry.revolute.repository

import java.util.concurrent.ConcurrentHashMap

import com.dmitry.revolute.domain.Entity

abstract class AbstractRepository[T <: Entity] extends Repository[T] {

  private val storage = new ConcurrentHashMap[String, T]()

  def get(id: String):T = storage.get(id)
  def put(entity: T): Unit = storage.put(entity.id, entity)

}
