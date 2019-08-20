package com.demien.mtransfer.domain

case class MTransfer(accIdFrom: Int, accIdTo: Int, amount: BigDecimal, state: String = "CREATED")
