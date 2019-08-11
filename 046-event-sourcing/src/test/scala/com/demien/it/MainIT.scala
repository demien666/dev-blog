package com.demien.it

import com.demien.ddd.Event
import com.demien.domain.account.AccountCommands.AccountCreateCommand
import com.demien.domain.account._
import com.demien.domain.moneyTransfer.MoneyTransferCommands.MoneyTransferCreateCommand
import com.demien.domain.moneyTransfer._
import com.demien.eventBus.InMemoryEventBus
import com.demien.eventStore.InMemoryEventStore
import com.demien.projection.{AccountProjectionEventHandler, InMemoryEventDrivenRepository, MoneyTransferProjectionEventHandler}
import org.scalatest.FunSuite

class MainIT extends FunSuite {

  val eventBus = new InMemoryEventBus[Event]
  val eventStore = new InMemoryEventStore[Event]

  val accountService = new AccountService(eventStore, Option(eventBus))
  val moneyTransferService = new MoneyTransferService(eventStore, Option(eventBus))

  val accountEventHandler = new AccountEventHandler(accountService)
  val moneyTransferEventHandler = new MoneyTransferEventHandler(moneyTransferService)

  // projection
  val accountRepository = new InMemoryEventDrivenRepository[Account, Event](Unit => AccountAggregate.newInstance(), (account, event) => AccountAggregate.applyEvent(account, event))
  val moneyTransferRepository = new InMemoryEventDrivenRepository[MoneyTransfer, Event](Unit => MoneyTransferAggregate.newInstance(), (transfer, event) => MoneyTransferAggregate.applyEvent(transfer, event))

  val accountProjectionEventHandler = new AccountProjectionEventHandler(accountRepository)
  val moneyTransferProjectionEventHandler = new MoneyTransferProjectionEventHandler(moneyTransferRepository)

  eventBus.registerEventHandler(accountProjectionEventHandler)
  eventBus.registerEventHandler(moneyTransferProjectionEventHandler)
  eventBus.registerEventHandler(accountEventHandler)
  eventBus.registerEventHandler(moneyTransferEventHandler)


  var index = 1;

  def getId(): Int = {
    index += 1
    index
  }

  def createAccount(accountNumber: String, balance: BigDecimal): Int = {
    val accountId = getId()
    accountService.process(AccountCreateCommand(accountId, AccountDetails(accountNumber, "USD"), balance))
    accountId
  }

  def createMoneyTransfer(accountId1: Int, accountId2: Int, transferAmount: BigDecimal): Int = {
    val transferId = getId()
    moneyTransferService.process(MoneyTransferCreateCommand(transferId, MoneyTransferDetails(accountId1, accountId2, transferAmount)))
    transferId
  }

  def transfer(acc1Balance: BigDecimal, acc2Balance: BigDecimal, transferAmount: BigDecimal):(Account, Account, MoneyTransfer) = {

    val accountId1 = createAccount("123", acc1Balance)
    val accountId2 = createAccount("234", acc2Balance)
    val transferId = createMoneyTransfer(accountId1, accountId2, transferAmount)

    val account1  = accountService.getEntity(accountId1)
    val account2  = accountService.getEntity(accountId2)
    val moneyTransfer = moneyTransferService.getEntity(transferId)

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

  test("Projection test") {
    val accId1 = createAccount("1001", 1000)
    val accId2 = createAccount("1002", 2000)
    val trId1 = createMoneyTransfer(accId1, accId2, 200)
    val account1 = accountRepository.getById(accId1)
    val account2 = accountRepository.getById(accId2)
    val tr = moneyTransferRepository.getById(trId1)

    assert(account1.balance === 800)
    assert(account2.balance === 2200)

    assert(tr.state === TransferState.COMPLETED)


  }


}
