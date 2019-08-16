package com.demien.projection.account

import com.demien.ddd.Event
import com.demien.domain.account.Account
import com.demien.domain.account.AccountEvents.{AccountCreatedEvent, AccountCreditedEvent, AccountDepositedEvent}
import com.demien.eventBus.EventHandler
import com.demien.projection.EventDrivenRepository

class AccountProjectionEventHandler(val repository: EventDrivenRepository[Account, Event]) extends EventHandler[Event] {
  override def getEventNamesToSubscribe(): Seq[String] = Seq(
    AccountCreatedEvent.getClass.getSimpleName,
    AccountDepositedEvent.getClass.getSimpleName,
    AccountCreditedEvent.getClass.getSimpleName
  )

  override def onEvent(event: Event): Unit = repository.applyEvent(event.aggregateId, event)
}
