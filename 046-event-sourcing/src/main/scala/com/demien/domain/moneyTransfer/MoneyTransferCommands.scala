package com.demien.domain.moneyTransfer

import com.demien.cqrs.Command

object MoneyTransferCommands {

  case class MoneyTransferCreateCommand(val moneyTransferId: Int, val moneyTransferDetails: MoneyTransferDetails) extends Command(moneyTransferId)

  case class MoneyTransferSetStateCreditedCommand(val moneyTransferId: Int) extends Command(moneyTransferId)

  case class MoneyTransferSetStateCompletedCommand(val moneyTransferId: Int) extends Command(moneyTransferId)

  case class MoneyTransferSetStateFailedCommand(val moneyTransferId: Int) extends Command(moneyTransferId)



}
