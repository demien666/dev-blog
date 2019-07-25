package com.demien.domain.account

import com.demien.ddd.Event
import com.demien.domain.account.AccountCommands.{CreditAccountCommand, DepositAccountCommand}
import com.demien.domain.moneyTransfer.MoneyTransferEvents.{MoneyTransferCreatedEvent, MoneyTransferStateCreditedEvent}
import com.demien.eventBus.EventHandler

class AccountEventHandler(val accountService: AccountService) extends EventHandler[Event] {
  override def onEvent(event: Event): Unit =
    event match {

      case MoneyTransferCreatedEvent(moneyTransferDetails, moneyTransferId)
      => accountService.process(CreditAccountCommand(moneyTransferDetails.accountIdFrom, moneyTransferDetails.amount, Option(moneyTransferId)))

      case MoneyTransferStateCreditedEvent(moneyTransferDetails, moneyTransferId)
      => accountService.process(DepositAccountCommand(moneyTransferDetails.accountIdTo, moneyTransferDetails.amount, Option(moneyTransferId)))

      case _ =>
    }
}
