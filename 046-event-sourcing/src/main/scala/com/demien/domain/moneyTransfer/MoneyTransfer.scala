package com.demien.domain.moneyTransfer

import com.demien.cqrs.Command
import com.demien.ddd.{Aggregate, Event}
import com.demien.domain.moneyTransfer.MoneyTransferCommands.{MoneyTransferCreateCommand, MoneyTransferSetStateCompletedCommand, MoneyTransferSetStateCreditedCommand, MoneyTransferSetStateFailedCommand}
import com.demien.domain.moneyTransfer.MoneyTransferEvents._
import com.demien.domain.moneyTransfer.TransferState.TransferState

object TransferState extends Enumeration {
  type TransferState = Value
  val CREATED, CREDITED, COMPLETED, FAILED = Value
}

case class MoneyTransferDetails(val accountIdFrom: Int, val accountIdTo: Int, val amount: BigDecimal)
case class MoneyTransfer(val moneyTransferDetails: MoneyTransferDetails, val state: TransferState = TransferState.CREATED)

// Aggregate
object MoneyTransferAggregate extends Aggregate[MoneyTransfer,Event] {

  override def applyEvent(entity: MoneyTransfer, event: Event): MoneyTransfer =
    event match {
      case MoneyTransferCreatedEvent(moneyTransferDetails, _)
        => MoneyTransfer(moneyTransferDetails, TransferState.CREATED)

      case MoneyTransferStateChangedToCreditedEvent(_, _)
        => entity.copy(state = TransferState.CREDITED)

      case MoneyTransferStateChangedToCompletedEvent(_, _)
      => entity.copy(state = TransferState.COMPLETED)

      case MoneyTransferStateChangedToFailedEvent(_, _)
      => entity.copy(state = TransferState.FAILED)

      case _ => entity
    }

  override def processCommand(aggregate: MoneyTransfer, command: Command): Seq[Event] =
    command match {
      case MoneyTransferCreateCommand(moneyTransferId, moneyTransferDetails)
        => Seq( MoneyTransferCreatedEvent(moneyTransferDetails, moneyTransferId))

      case MoneyTransferSetStateCreditedCommand(moneyTransferId)
      => Seq(MoneyTransferStateChangedToCreditedEvent(aggregate.moneyTransferDetails, moneyTransferId))

      case MoneyTransferSetStateCompletedCommand(moneyTransferId)
      => Seq(MoneyTransferStateChangedToCompletedEvent(aggregate.moneyTransferDetails, moneyTransferId))

      case MoneyTransferSetStateFailedCommand(moneyTransferId)
      => Seq(MoneyTransferStateChangedToFailedEvent(aggregate.moneyTransferDetails, moneyTransferId))

      case _ => unknownCommand(command)
    }

  override def newInstance(): MoneyTransfer = new MoneyTransfer(null, null)
}
