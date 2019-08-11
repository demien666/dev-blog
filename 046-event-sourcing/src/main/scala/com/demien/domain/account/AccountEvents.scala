package com.demien.domain.account

import com.demien.ddd.Event

object AccountEvents {


  case class AccountCreatedEvent(val accountId: Int, val accountDetails: AccountDetails, balance: BigDecimal) extends Event(accountId)

  case class AccountDepositedEvent(val accountId: Int, val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends Event(accountId)

  case class AccountCreditedEvent(val accountId: Int, val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends Event(accountId)

  case class AccountCreditFailedInsufficientFundsEvent(val accountId: Int, val moneyTransferId: Option[Int] = Option.empty) extends Event(accountId)

}
