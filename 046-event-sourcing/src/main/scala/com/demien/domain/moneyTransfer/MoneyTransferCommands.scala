package com.demien.domain.moneyTransfer

import com.demien.cqrs.Command

object MoneyTransferCommands {

  case class MoneyTransferCreateCommand(val moneyTransferId: Int, val moneyTransferDetails: MoneyTransferDetails) extends Command(moneyTransferId)

  case class MoneyTransferStateCreditedCommand(val moneyTransferId: Int) extends Command(moneyTransferId)

  case class MoneyTransferStateCompletedCommand(val moneyTransferId: Int) extends Command(moneyTransferId)

  case class MoneyTransferStateFailedCommand(val moneyTransferId: Int) extends Command(moneyTransferId)



}
