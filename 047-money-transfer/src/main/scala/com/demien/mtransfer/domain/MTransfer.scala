package com.demien.mtransfer.domain

object MTransfer {
  val CREATED = "CREATED"
  val FAILED = "FAILED"
  val COMPLETED = "COMPLETED"
}

case class MTransfer(accIdFrom: Int, accIdTo: Int, amount: BigDecimal, state: String = MTransfer.CREATED)
