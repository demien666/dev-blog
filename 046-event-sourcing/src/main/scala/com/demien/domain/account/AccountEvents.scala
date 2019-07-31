package com.demien.domain.account

import com.demien.ddd.Event

object AccountEvents {

  sealed trait AccountEvent extends Event

  case class AccountCreatedEvent(val accountDetails: AccountDetails, balance: BigDecimal) extends AccountEvent

  case class AccountDepositedEvent(val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends AccountEvent

  case class AccountCreditedEvent(val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends AccountEvent

  case class AccountCreditFailedInsufficientFundsEvent(val moneyTransferId: Option[Int] = Option.empty) extends AccountEvent

}
