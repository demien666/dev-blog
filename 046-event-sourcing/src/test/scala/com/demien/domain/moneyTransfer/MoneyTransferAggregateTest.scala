package com.demien.domain.moneyTransfer

import com.demien.domain.moneyTransfer.MoneyTransferCommands.{MoneyTransferCreateCommand, MoneyTransferStateCompletedCommand, MoneyTransferStateCreditedCommand}
import com.demien.domain.moneyTransfer.MoneyTransferEvents.{MoneyTransferCreatedEvent, MoneyTransferStateCompletedEvent, MoneyTransferStateCreditedEvent}
import org.scalatest.FunSuite

class MoneyTransferAggregateTest extends FunSuite {

  val details = MoneyTransferDetails(10,20, BigDecimal(30))
  val transactionId = 1
  val transfer = new MoneyTransfer(details)

  test("testApplyEvent") {



    val created = MoneyTransferAggregate.applyEvent(transfer, MoneyTransferCreatedEvent(details, transactionId))
    assert(created.state === TransferState.CREATED)
    assert(created.moneyTransferDetails === details)

    val credited = MoneyTransferAggregate.applyEvent(transfer, MoneyTransferStateCreditedEvent(details, transactionId))
    assert(credited.state === TransferState.CREDITED)
    assert(credited.moneyTransferDetails === details)

    val completed = MoneyTransferAggregate.applyEvent(transfer, MoneyTransferStateCompletedEvent(details))
    assert(completed.state === TransferState.COMPLETED)
    assert(completed.moneyTransferDetails === details)


  }

  test("testProcessCommand") {

    val created = MoneyTransferAggregate.processCommand(transfer, MoneyTransferCreateCommand(transactionId, details))
    assert( created === Seq(MoneyTransferCreatedEvent(details, transactionId)))

    val credited = MoneyTransferAggregate.processCommand(transfer, MoneyTransferStateCreditedCommand(transactionId) )
    assert( credited === Seq(MoneyTransferStateCreditedEvent(details, transactionId)))

    val completed = MoneyTransferAggregate.processCommand(transfer, MoneyTransferStateCompletedCommand(transactionId))
    assert( completed === Seq(MoneyTransferStateCompletedEvent(details)))

  }

}
