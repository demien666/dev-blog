package com.demien.domain.moneyTransfer

import com.demien.ddd.Event

object MoneyTransferEvents {


  case class MoneyTransferCreatedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends Event(moneyTransferId)

  case class MoneyTransferStateChangedToCreditedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends Event(moneyTransferId)

  case class MoneyTransferStateChangedToCompletedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends Event(moneyTransferId)

  case class MoneyTransferStateChangedToFailedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends Event(moneyTransferId)

}
