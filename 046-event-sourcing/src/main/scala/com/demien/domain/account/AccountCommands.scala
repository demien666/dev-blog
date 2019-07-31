package com.demien.domain.account

import com.demien.cqrs.Command

object AccountCommands {

  case class AccountCreateCommand(val accountId: Int, val accountDetails: AccountDetails, val balance: BigDecimal) extends Command(accountId)

  case class AccountCreditCommand(val accountId: Int, val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends Command(accountId)

  case class AccountDepositCommand(val accountId: Int, val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends Command(accountId)

}
