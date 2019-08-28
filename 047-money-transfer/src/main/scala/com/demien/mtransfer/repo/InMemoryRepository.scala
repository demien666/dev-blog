package com.demien.mtransfer.repo

import java.util.concurrent.ConcurrentHashMap

class InMemoryRepository[T] extends Repository[T] {

  val storage: ConcurrentHashMap[Int, T] = new ConcurrentHashMap[Int, T]()
  var index: Int = 0

  override def getById(id: Int): T = storage.get(id)

  override def save(entity: T): Int = {
    index.synchronized {
      index = index + 1
      storage.put(index, entity)
      index
    }
  }

  def sortToAvoidDeadLock(seq: Seq[Int]): Seq[Int] = seq.sorted

  override def bulkUpdate(ops: Seq[(Int, T => T)]): Unit = {
    val ids = ops.map(op => op._1)
    bulkUpdateInternal(sortToAvoidDeadLock(ids), ops, Map[Int, T]())
  }

  private def bulkUpdateInternal(idsForSync: Seq[Int], idsAndOps: Seq[(Int, T => T)], backup: Map[Int, T]): Unit = {
    if (!idsForSync.isEmpty) {
      val headId = idsForSync.head
      headId.synchronized {
        bulkUpdateInternal(idsForSync.tail, idsAndOps, backup + (headId -> storage.get(headId)))
      }
    } else {
      try {
        idsAndOps.foreach(op => {
          val entity = storage.get(op._1)
          val updated = op._2(entity)
          storage.put(op._1, updated)
        })
      } catch {
        case ex: Throwable => {
          ex.printStackTrace()
          backup.foreach(b => storage.put(b._1, b._2))
          throw OperationExecutionException()
        }
      }
    }
  }

  override def find(predicate: T => Boolean): Seq[(Int, T)] = {
    var result = Seq[(Int, T)]()
    storage.keySet().forEach(k => {
      val v = storage.get(k)
      if (predicate(v)) result = result ++ Seq((k, v))
    })
    result
  }

}
