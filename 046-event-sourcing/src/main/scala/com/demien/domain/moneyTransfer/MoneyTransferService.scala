package com.demien.domain.moneyTransfer

import com.demien.ddd.{Event, Service}
import com.demien.domain.moneyTransfer.MoneyTransferCommands.{MoneyTransferCreateCommand, MoneyTransferStateCompletedCommand, MoneyTransferStateCreditedCommand, MoneyTransferStateFailedCommand}
import com.demien.domain.moneyTransfer.MoneyTransferEvents.MoneyTransferEvent
import com.demien.eventBus.EventBus
import com.demien.eventStore.{EventStore, InMemoryEventStore}

class MoneyTransferService(eventStore: EventStore[Event], eventBus: Option[EventBus[Event]] = Option.empty)
  extends Service[MoneyTransfer,Event](eventStore, MoneyTransferAggregate, eventBus) {

  def createMoneyTransfer(moneyTransferDetails: MoneyTransferDetails): Int = {
    val newId = eventStore.generateId()
    process(newId, new MoneyTransferCreateCommand(moneyTransferDetails, newId))
    newId
  }

  def stateCredited(moneyTransferId: Int): Unit =
    process(moneyTransferId, new MoneyTransferStateCreditedCommand(moneyTransferId))

  def stateCompleted(moneyTransferId: Int): Unit =
    process(moneyTransferId, new MoneyTransferStateCompletedCommand())

  def stateFailed(moneyTransferId: Int): Unit =
    process(moneyTransferId, new MoneyTransferStateFailedCommand())


}
