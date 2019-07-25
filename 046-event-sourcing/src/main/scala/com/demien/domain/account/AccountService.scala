package com.demien.domain.account

import com.demien.ddd.{Event, Service}
import com.demien.domain.account.AccountCommands.{CreateAccountCommand, CreditAccountCommand, DepositAccountCommand}
import com.demien.domain.account.AccountEvents.AccountEvent
import com.demien.eventBus.EventBus
import com.demien.eventStore.EventStore

class AccountService(eventStore: EventStore[Event], eventBus: Option[EventBus[Event]]= Option.empty)
     extends Service[Account,Event](eventStore, AccountAggregate, eventBus){

  def createAccount(accountDetails: AccountDetails, balance: BigDecimal): Int = {
    val newId = eventStore.generateId()
    process(newId, CreateAccountCommand( accountDetails, balance))
    newId
  }

  def creditAccount(accountId: Int, amount: BigDecimal, moneyTransferId: Option[Int] = Option.empty): Unit =
    process(accountId, CreditAccountCommand(amount, moneyTransferId))

  def debitAccount(accountId: Int, amount: BigDecimal, moneyTransferId: Option[Int] = Option.empty): Unit =
    process(accountId, DepositAccountCommand(amount, moneyTransferId))


}
