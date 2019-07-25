package com.demien.domain.account

import com.demien.ddd.{Event, Service}
import com.demien.eventBus.EventBus
import com.demien.eventStore.EventStore

class AccountService(eventStore: EventStore[Event], eventBus: Option[EventBus[Event]]= Option.empty)
     extends Service[Account,Event](eventStore, AccountAggregate, eventBus){

}
