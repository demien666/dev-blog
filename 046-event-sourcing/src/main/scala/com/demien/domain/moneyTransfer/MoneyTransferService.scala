package com.demien.domain.moneyTransfer

import com.demien.ddd.{Event, Service}
import com.demien.eventBus.EventBus
import com.demien.eventStore.EventStore

class MoneyTransferService(eventStore: EventStore[Event], eventBus: Option[EventBus[Event]] = Option.empty)
  extends Service[MoneyTransfer,Event](eventStore, MoneyTransferAggregate, eventBus) {


}
