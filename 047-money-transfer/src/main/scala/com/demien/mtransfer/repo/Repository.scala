package com.demien.mtransfer.repo

trait Repository[T] {

  def getById(id: Int): T

  def save(entity: T): Int

  def update(id: Int, entity: T): Unit = bulkUpdate(Seq((id, _ => entity)))

  def bulkUpdate(ops: Seq[(Int, T => T)]): Unit

  def find(predicate: T => Boolean): Seq[(Int, T)]

}
