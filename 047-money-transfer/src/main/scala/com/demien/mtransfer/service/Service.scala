package com.demien.mtransfer.service

import com.demien.mtransfer.repo.Repository

abstract class Service[T](repo: Repository[T]) extends Repository[T] {

  override def getById(id: Int): T = repo.getById(id)

  override def save(entity: T): Int = repo.save(entity)

  override def bulkUpdate(ops: Seq[(Int, T => T)]): Unit = repo.bulkUpdate(ops)

  override def find(predicate: T => Boolean): Seq[(Int, T)] = repo.find(predicate)
}
