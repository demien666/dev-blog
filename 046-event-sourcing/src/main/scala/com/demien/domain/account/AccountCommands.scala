package com.demien.domain.account

import com.demien.cqrs.Command

object AccountCommands {

  sealed trait AccountCommand extends Command

    case class CreateAccountCommand(val accountDetails: AccountDetails, val balance: BigDecimal) extends AccountCommand
    case class CreditAccountCommand(val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends AccountCommand
    case class DepositAccountCommand(val amount: BigDecimal, val moneyTransferId: Option[Int] = Option.empty) extends AccountCommand


}
