package com.demien.it

import com.demien.ddd.Event
import com.demien.domain.account.AccountCommands.AccountCreateCommand
import com.demien.domain.account.{Account, AccountDetails, AccountEventHandler, AccountService}
import com.demien.domain.moneyTransfer.MoneyTransferCommands.MoneyTransferCreateCommand
import com.demien.domain.moneyTransfer._
import com.demien.eventBus.InMemoryEventBus
import com.demien.eventStore.InMemoryEventStore
import org.scalatest.FunSuite

class MainIT extends FunSuite {

  val eventBus = new InMemoryEventBus[Event]
  val eventStore = new InMemoryEventStore[Event]

  val accountService = new AccountService(eventStore, Option(eventBus))
  val transferService = new MoneyTransferService(eventStore, Option(eventBus))

  val accountEventHandler = new AccountEventHandler(accountService)
  val transferEventHandler = new MoneyTransferEventHandler(transferService)

  eventBus.registerEventHandler(accountEventHandler)
  eventBus.registerEventHandler(transferEventHandler)

  var index = 1;

  def getId(): Int = {
    index += 1
    index
  }

  def transfer(acc1Balance: BigDecimal, acc2Balance: BigDecimal, transferAmount: BigDecimal):(Account, Account, MoneyTransfer) = {

    val accountId1 = getId()
    accountService.process(AccountCreateCommand(accountId1, AccountDetails("123", "USD"), acc1Balance))

    val accountId2 = getId()
    accountService.process(AccountCreateCommand(accountId2, AccountDetails("234", "USD"), acc2Balance))

    val transferId = getId()
    transferService.process(MoneyTransferCreateCommand(transferId, MoneyTransferDetails(accountId1, accountId2, transferAmount)))

    val account1  = accountService.getEntity(accountId1)
    val account2  = accountService.getEntity(accountId2)
    val moneyTransfer = transferService.getEntity(transferId)

    (account1, account2, moneyTransfer)

  }


  test("Happy path: correct money transfer") {
    val (account1, account2, moneyTransfer)  = transfer(100, 200, 50)
    assert(account1.balance === 50)
    assert(account2.balance === 250)
    assert(moneyTransfer.state === TransferState.COMPLETED)
  }

  test("UnHappy path: not enough money for transfer") {
    val (account1, account2, moneyTransfer)  = transfer(100, 200, 150)
    assert(account1.balance === 100)
    assert(account2.balance === 200)
    assert(moneyTransfer.state === TransferState.FAILED)
  }


}
