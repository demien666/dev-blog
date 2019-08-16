package com.demien.projection.moneyTransfer

import com.demien.ddd.Event
import com.demien.domain.moneyTransfer.MoneyTransfer
import com.demien.domain.moneyTransfer.MoneyTransferEvents.{MoneyTransferCreatedEvent, MoneyTransferStateChangedToCompletedEvent, MoneyTransferStateChangedToCreditedEvent, MoneyTransferStateChangedToFailedEvent}
import com.demien.eventBus.EventHandler
import com.demien.projection.EventDrivenRepository

class MoneyTransferProjectionEventHandler(val repository: EventDrivenRepository[MoneyTransfer, Event]) extends EventHandler[Event] {
  override def getEventNamesToSubscribe(): Seq[String] = Seq(
    MoneyTransferCreatedEvent.getClass.getSimpleName,
    MoneyTransferStateChangedToCreditedEvent.getClass.getSimpleName,
    MoneyTransferStateChangedToCompletedEvent.getClass.getSimpleName,
    MoneyTransferStateChangedToFailedEvent.getClass.getSimpleName
  )

  override def onEvent(event: Event): Unit = repository.applyEvent(event.aggregateId, event)
}
