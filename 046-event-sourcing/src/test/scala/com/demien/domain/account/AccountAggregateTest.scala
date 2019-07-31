package com.demien.domain.account

import com.demien.cqrs.Command
import com.demien.ddd.Event
import com.demien.domain.account.AccountCommands.{AccountCreateCommand, AccountCreditCommand, AccountDepositCommand}
import com.demien.domain.account.AccountEvents.{AccountCreatedEvent, AccountCreditFailedInsufficientFundsEvent, AccountCreditedEvent, AccountDepositedEvent}
import org.scalatest.FunSuite

class AccountAggregateTest extends FunSuite {

  def checkCmdEvt(account: Account, cmd: Command, evt: Event): Unit = {
    val events = AccountAggregate.processCommand(account, cmd)
    assert(events(0) === evt)
  }

  test("testProcessCommand") {

    checkCmdEvt(
      AccountAggregate.newInstance()
      , AccountCreateCommand(-1, AccountDetails("12345", "USD"), 10)
      , AccountCreatedEvent(AccountDetails("12345", "USD"), 10)
    )

    val account = Account(AccountDetails("12345", "USD"), 10)
    val transId = Option(1)
    checkCmdEvt(account, AccountCreditCommand(-1, 7, transId), AccountCreditedEvent(7, transId))
    checkCmdEvt(account, AccountCreditCommand(-1, 11, transId), AccountCreditFailedInsufficientFundsEvent(transId))
    checkCmdEvt(account, AccountDepositCommand(-1, 3, transId), AccountDepositedEvent(3, transId))

  }

  test("testApplyEvent") {
    val account =
      Seq(
        AccountCreatedEvent(AccountDetails("12345", "USD"), 10),
        AccountDepositedEvent(3),
        AccountCreditedEvent(8)
      ).foldLeft(AccountAggregate.newInstance()) {
        (acc, evt) => AccountAggregate.applyEvent(acc, evt)
      }

    assert(account.accountDetails.accountNumber === "12345")
    assert(account.accountDetails.accountCurrency === "USD")
    assert(account.balance === 5)
  }

}
