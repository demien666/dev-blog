package com.demien.domain.moneyTransfer

import com.demien.ddd.Event
import com.demien.domain.account.AccountEvents.{AccountCreditFailedInsufficientFundsEvent, AccountCreditedEvent, AccountDepositedEvent}
import com.demien.domain.moneyTransfer.MoneyTransferCommands.{MoneyTransferSetStateCompletedCommand, MoneyTransferSetStateCreditedCommand, MoneyTransferSetStateFailedCommand}
import com.demien.eventBus.EventHandler

class MoneyTransferEventHandler(moneyTransferService: MoneyTransferService) extends EventHandler[Event] {
  override def onEvent(event: Event): Unit =
    event match {
      case AccountCreditedEvent(_, moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.process(MoneyTransferSetStateCreditedCommand(moneyTransferId.get))

      case AccountDepositedEvent(_, moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.process(MoneyTransferSetStateCompletedCommand(moneyTransferId.get))

      case AccountCreditFailedInsufficientFundsEvent(moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.process(MoneyTransferSetStateFailedCommand(moneyTransferId.get))

      case _ =>
    }

  override def getEventNamesToSubscribe(): Seq[String] =
    Seq(
      AccountCreditedEvent.getClass.getSimpleName,
      AccountDepositedEvent.getClass.getSimpleName,
      AccountCreditFailedInsufficientFundsEvent.getClass.getSimpleName
    )
}
