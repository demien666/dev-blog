package com.demien.domain.moneyTransfer

import com.demien.cqrs.Command
import com.demien.ddd.{Aggregate, Event}
import com.demien.domain.moneyTransfer.MoneyTransferCommands.{MoneyTransferCreateCommand, MoneyTransferStateCompletedCommand, MoneyTransferStateCreditedCommand, MoneyTransferStateFailedCommand}
import com.demien.domain.moneyTransfer.MoneyTransferEvents.{MoneyTransferCreatedEvent, MoneyTransferEvent, MoneyTransferStateCompletedEvent, MoneyTransferStateCreditedEvent, MoneyTransferStateFailedEvent}
import com.demien.domain.moneyTransfer.TransferState.TransferState

object TransferState extends Enumeration {
  type TransferState = Value
  val CREATED, CREDITED, COMPLETED, FAILED = Value
}

// Value Object
case class MoneyTransferDetails(val accountIdFrom: Int, val accountIdTo: Int, val amount: BigDecimal)

// Entity
case class MoneyTransfer(val moneyTransferDetails: MoneyTransferDetails, val state: TransferState = TransferState.CREATED)

// Aggregate
object MoneyTransferAggregate extends Aggregate[MoneyTransfer,Event] {

  override def applyEvent(entity: MoneyTransfer, event: Event): MoneyTransfer =
    event match {
      case MoneyTransferCreatedEvent(moneyTransferDetails, _)
        => MoneyTransfer(moneyTransferDetails, TransferState.CREATED)

      case MoneyTransferStateCreditedEvent(_, _)
        => entity.copy(state = TransferState.CREDITED)

      case MoneyTransferStateCompletedEvent(_)
      => entity.copy(state = TransferState.COMPLETED)

      case MoneyTransferStateFailedEvent(_)
      => entity.copy(state = TransferState.FAILED)

      case _ => entity
    }

  override def processCommand(aggregate: MoneyTransfer, command: Command): Seq[MoneyTransferEvent] =
    command match {
      case MoneyTransferCreateCommand(moneyTransferDetails, moneyTransferId)
        => Seq( MoneyTransferCreatedEvent(moneyTransferDetails, moneyTransferId))

      case MoneyTransferStateCreditedCommand(moneyTransferId)
        => Seq(MoneyTransferStateCreditedEvent(aggregate.moneyTransferDetails, moneyTransferId))

      case MoneyTransferStateCompletedCommand()
      => Seq(MoneyTransferStateCompletedEvent(aggregate.moneyTransferDetails))

      case MoneyTransferStateFailedCommand()
      => Seq(MoneyTransferStateFailedEvent(aggregate.moneyTransferDetails))

      case _ => unknownCommand(command)
    }

  override def newInstance(): MoneyTransfer = new MoneyTransfer(null, null)
}
