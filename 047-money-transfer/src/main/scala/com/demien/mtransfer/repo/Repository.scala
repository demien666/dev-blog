package com.demien.mtransfer.repo

trait Repository[T] {

  def getById(id: Int): T

  def save(entity: T): Int

  def update(id: Int, mutator: T => T): Unit

  def biUpdate(id1: Int, mutator1: T => T, id2: Int, mutator2: T => T): Unit

  def find(predicate: T => Boolean): Seq[(Int, T)]

  def history(id: Int): Seq[T]

}
