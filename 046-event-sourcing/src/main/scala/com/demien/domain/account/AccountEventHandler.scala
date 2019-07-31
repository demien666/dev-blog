package com.demien.domain.account

import com.demien.ddd.Event
import com.demien.domain.account.AccountCommands.{AccountCreditCommand, AccountDepositCommand}
import com.demien.domain.moneyTransfer.MoneyTransferEvents.{MoneyTransferCreatedEvent, MoneyTransferStateChangedToCreditedEvent}
import com.demien.eventBus.EventHandler

class AccountEventHandler(val accountService: AccountService) extends EventHandler[Event] {
  override def onEvent(event: Event): Unit =
    event match {

      case MoneyTransferCreatedEvent(moneyTransferDetails, moneyTransferId)
      => accountService.process(AccountCreditCommand(moneyTransferDetails.accountIdFrom, moneyTransferDetails.amount, Option(moneyTransferId)))

      case MoneyTransferStateChangedToCreditedEvent(moneyTransferDetails, moneyTransferId)
      => accountService.process(AccountDepositCommand(moneyTransferDetails.accountIdTo, moneyTransferDetails.amount, Option(moneyTransferId)))

      case _ =>
    }

  override def getEventNamesToSubscribe(): Seq[String] =
    Seq(
      MoneyTransferCreatedEvent.getClass.getSimpleName,
      MoneyTransferStateChangedToCreditedEvent.getClass.getSimpleName
    )
}
