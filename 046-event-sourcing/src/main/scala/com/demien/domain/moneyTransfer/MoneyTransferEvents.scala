package com.demien.domain.moneyTransfer

import com.demien.ddd.Event

object MoneyTransferEvents {

  sealed trait MoneyTransferEvent extends Event
  case class MoneyTransferCreatedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends MoneyTransferEvent

  case class MoneyTransferStateChangedToCreditedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends MoneyTransferEvent

  case class MoneyTransferStateChangedToCompletedEvent(val moneyTransferDetails: MoneyTransferDetails) extends MoneyTransferEvent

  case class MoneyTransferStateChangedToFailedEvent(val moneyTransferDetails: MoneyTransferDetails) extends MoneyTransferEvent

}
