package com.demien.domain.account

import com.demien.cqrs.Command
import com.demien.ddd.Event
import com.demien.domain.account.AccountCommands.{CreateAccountCommand, CreditAccountCommand, DepositAccountCommand}
import com.demien.domain.account.AccountEvents.{AccountCreated, AccountCreditFailedInsufficientFunds, AccountCreditPerformed, AccountDepositPerformed}
import org.scalatest.FunSuite

class AccountAggregateTest extends FunSuite {

  def checkCmdEvt(account: Account, cmd: Command, evt: Event): Unit = {
    val events = AccountAggregate.processCommand(account, cmd)
    assert(events(0) === evt)
  }

  test("testProcessCommand") {

    checkCmdEvt(
      AccountAggregate.newInstance()
      , CreateAccountCommand(AccountDetails("12345", "USD"), 10)
      , AccountCreated(AccountDetails("12345", "USD"), 10)
    )

    val account = Account(AccountDetails("12345", "USD"), 10)
    val transId = Option(1)
    checkCmdEvt(account, CreditAccountCommand(7, transId), AccountCreditPerformed(7, transId))
    checkCmdEvt(account, CreditAccountCommand(11, transId), AccountCreditFailedInsufficientFunds(transId))
    checkCmdEvt(account, DepositAccountCommand(3, transId), AccountDepositPerformed(3, transId))

  }

  test("testApplyEvent") {
    val account =
      Seq(
        AccountCreated(AccountDetails("12345", "USD"), 10),
        AccountDepositPerformed(3),
        AccountCreditPerformed(8)
      ).foldLeft(AccountAggregate.newInstance()) {
        (acc, evt) => AccountAggregate.applyEvent(acc, evt)
      }

    assert(account.accountDetails.accountNumber === "12345")
    assert(account.accountDetails.accountCurrency === "USD")
    assert(account.balance === 5)
  }

}
