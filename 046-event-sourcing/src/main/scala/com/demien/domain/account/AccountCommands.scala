package com.demien.domain.account

import com.demien.cqrs.Command

object AccountCommands {

    case class CreateAccountCommand(val accountId: Int, val accountDetails: AccountDetails, val balance: BigDecimal) extends Command(accountId)

    case class CreditAccountCommand(val accountId: Int, val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends Command(accountId)

    case class DepositAccountCommand(val accountId: Int, val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends Command(accountId)

}
