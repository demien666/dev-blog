package com.demien.mtransfer.repo

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.mutable.ArrayBuffer

class InMemoryRepository[T] extends Repository[T] {

  val storage: ConcurrentHashMap[Int, ArrayBuffer[T]] = new ConcurrentHashMap[Int, ArrayBuffer[T]]()
  val index: AtomicInteger = new AtomicInteger(0)

  override def getById(id: Int): T = storage.get(id).last

  override def save(entity: T): Int = {
    val newId = index.incrementAndGet()
    storage.put(newId, new ArrayBuffer().+=(entity))
    newId
  }

  def update(id: Int, mutator: T => T): Unit = storage.computeIfPresent(id, (a, b) => b += mutator(b.last))

  def biUpdate(id1: Int, mutator1: T => T, id2: Int, mutator2: T => T): Unit = {
    if (id1 > id2) biUpdateOrderedToAvoidDeadlock(id1, mutator1, id2, mutator2)
    else biUpdateOrderedToAvoidDeadlock(id2, mutator2, id1, mutator1)
  }

  private def biUpdateOrderedToAvoidDeadlock(id1: Int, mutator1: T => T, id2: Int, mutator2: T => T): Unit = {
    val (history1, history2) = (storage.get(id1), storage.get(id2))

    history1.synchronized {
      history2.synchronized {
        val (oldVal1, oldVal2) = (history1.last, history2.last)
        val (newVal1, newVal2) = (mutator1(oldVal1), mutator2(oldVal2))
        history1 += newVal1
        history2 += newVal2

      }
    }
  }

  override def find(predicate: T => Boolean): Seq[(Int, T)] = {
    var result = Seq[(Int, T)]()
    storage.keySet().forEach(k => {
      val v = storage.get(k).last
      if (predicate(v)) result = result ++ Seq((k, v))
    })
    result
  }

}
