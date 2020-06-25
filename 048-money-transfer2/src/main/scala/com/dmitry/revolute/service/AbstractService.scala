package com.dmitry.revolute.service

import com.dmitry.revolute.domain.{Entity, Operation}
import com.dmitry.revolute.repository.Repository
import org.apache.log4j.LogManager

abstract class AbstractService[T <: Entity](params: ServiceParams = ServiceParams.default) {

  val logger = LogManager.getLogger(this.getClass)

  private def sortToAvoidDeadlock(op1: Operation[T], op2: Operation[T]): (Operation[T], Operation[T]) =
    if (op1.entity.id > op2.entity.id) (op1, op2) else (op2, op1)

  private def sleepIfNeeded(): Unit = if (params.isSlowMode) Thread.sleep(3000)

  def doInTransaction(repository: Repository[T],  op1: Operation[T], op2: Operation[T]): Unit = {
    val (sop1,sop2) = if (params.isDeadlockProtected) sortToAvoidDeadlock(op1, op2) else (op1, op2)
    val entity1Id = repository.getFromEntity(sop1.entity).id
    val entity2Id = repository.getFromEntity(sop2.entity).id

    val opsDesc = op1.description + "," + op2.description

    logger.trace(s"Trying to lock entity #$entity1Id for operations: $opsDesc ")
    entity1Id.synchronized {
      logger.debug(s"Locked entity #$entity1Id for operation: $opsDesc ")
      sleepIfNeeded()
      logger.trace(s"Trying to lock entity #$entity2Id for operation: $opsDesc ")
      entity2Id.synchronized {
        logger.debug(s"Locked entity #$entity2Id for operation: $opsDesc")
        val entity1latest = repository.get(entity1Id)
        val entity2latest = repository.get(entity2Id)
        try {
          repository.put(sop1.mutator(entity1latest))
          repository.put(sop2.mutator(entity2latest))
        } catch {
          case ex: Throwable => {
            logger.error(ex.getMessage)
            logger.info("Restoring previous values")
            repository.put(entity1latest)
            repository.put(entity2latest)
            throw ex
          }
        }
      }
    }

  }

}
