package com.demien.domain.moneyTransfer

import com.demien.cqrs.Command

object MoneyTransferCommands {

  sealed trait MoneyTransferCommand extends Command
  case class MoneyTransferCreateCommand(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends MoneyTransferCommand
  case class MoneyTransferStateCreditedCommand(val moneyTransferId: Int) extends MoneyTransferCommand
  case class MoneyTransferStateCompletedCommand() extends MoneyTransferCommand
  case class MoneyTransferStateFailedCommand() extends MoneyTransferCommand



}
