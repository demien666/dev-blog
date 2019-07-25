package com.demien.domain.account

import com.demien.ddd.Event

object AccountEvents {

  sealed trait AccountEvent extends Event
  case class AccountCreated(val accountDetails: AccountDetails, balance: BigDecimal) extends AccountEvent
  case class AccountDepositPerformed(val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends AccountEvent
  case class AccountCreditPerformed(val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends AccountEvent
  case class AccountCreditFailedInsufficientFunds(val moneyTransferId: Option[Int] = Option.empty) extends AccountEvent

}
