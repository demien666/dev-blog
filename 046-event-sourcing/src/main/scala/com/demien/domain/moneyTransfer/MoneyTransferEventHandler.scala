package com.demien.domain.moneyTransfer

import com.demien.ddd.Event
import com.demien.domain.account.AccountEvents.{AccountCreditFailedInsufficientFunds, AccountCreditPerformed, AccountDepositPerformed}
import com.demien.domain.moneyTransfer.MoneyTransferCommands.{MoneyTransferStateCompletedCommand, MoneyTransferStateCreditedCommand, MoneyTransferStateFailedCommand}
import com.demien.eventBus.EventHandler

class MoneyTransferEventHandler(moneyTransferService: MoneyTransferService) extends EventHandler[Event] {
  override def onEvent(event: Event): Unit =
    event match {
      case AccountCreditPerformed(_, moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.process(MoneyTransferStateCreditedCommand(moneyTransferId.get))

      case AccountDepositPerformed(_, moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.process(MoneyTransferStateCompletedCommand(moneyTransferId.get))

      case AccountCreditFailedInsufficientFunds(moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.process(MoneyTransferStateFailedCommand(moneyTransferId.get))

      case _ =>
    }
}
