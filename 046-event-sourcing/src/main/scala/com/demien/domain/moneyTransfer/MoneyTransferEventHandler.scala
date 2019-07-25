package com.demien.domain.moneyTransfer

import com.demien.ddd.Event
import com.demien.domain.account.AccountEvents.{AccountCreditFailedInsufficientFunds, AccountCreditPerformed, AccountDepositPerformed}
import com.demien.eventBus.EventHandler

class MoneyTransferEventHandler(moneyTransferService: MoneyTransferService) extends EventHandler[Event] {
  override def onEvent(event: Event): Unit =
    event match {
      case AccountCreditPerformed(_, moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.stateCredited(moneyTransferId.get)

      case AccountDepositPerformed(_, moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.stateCompleted(moneyTransferId.get)

      case AccountCreditFailedInsufficientFunds(moneyTransferId)
      => if (moneyTransferId.nonEmpty) moneyTransferService.stateFailed(moneyTransferId.get)

      case _ =>
    }
}
