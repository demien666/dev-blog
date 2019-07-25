package com.demien.domain.moneyTransfer

import com.demien.ddd.Event
import com.demien.domain.moneyTransfer.TransferState.TransferState

object MoneyTransferEvents {

  sealed trait MoneyTransferEvent extends Event
  case class MoneyTransferCreatedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends MoneyTransferEvent
  case class MoneyTransferStateCreditedEvent(val moneyTransferDetails: MoneyTransferDetails, val moneyTransferId: Int) extends MoneyTransferEvent
  case class MoneyTransferStateCompletedEvent(val moneyTransferDetails: MoneyTransferDetails) extends MoneyTransferEvent
  case class MoneyTransferStateFailedEvent(val moneyTransferDetails: MoneyTransferDetails) extends MoneyTransferEvent

}
