package com.demien.domain.account

import com.demien.cqrs.Command

object AccountCommands {

  case class AccountCreateCommand(accountId: Int, accountDetails: AccountDetails, balance: BigDecimal) extends Command(accountId)

  case class AccountCreditCommand(accountId: Int, amount: BigDecimal, moneyTransferId: Option[Int] = Option.empty) extends Command(accountId)

  case class AccountDepositCommand(accountId: Int, amount: BigDecimal, moneyTransferId: Option[Int] = Option.empty) extends Command(accountId)

}
