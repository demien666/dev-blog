package com.demien.mtransfer.service

import com.demien.mtransfer.repo.Repository

abstract class Service[T](repo: Repository[T]) {

  def getById(id: Int): T = repo.getById(id)

  def save(entity: T): Int = repo.save(entity)

  def update(id: Int, mutator: T => T): Unit = repo.update(id, mutator)

  def biUpdate(id1: Int, mutator1: T => T, id2: Int, mutator2: T => T): Unit = repo.biUpdate(id1, mutator1, id2, mutator2)

  def find(predicate: T => Boolean): Seq[(Int, T)] = repo.find(predicate)
}
