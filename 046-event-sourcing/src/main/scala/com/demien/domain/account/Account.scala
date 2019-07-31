package com.demien.domain.account

import com.demien.cqrs.Command
import com.demien.ddd.{Aggregate, Event}
import com.demien.domain.account.AccountCommands.{AccountCreateCommand, AccountCreditCommand, AccountDepositCommand}
import com.demien.domain.account.AccountEvents._

case class AccountDetails(val accountNumber: String, val accountCurrency: String)
case class Account(val accountDetails: AccountDetails, val balance: BigDecimal)

object AccountAggregate extends Aggregate[Account,Event] {

   override def processCommand(account: Account, command: Command): Seq[AccountEvent] =
    command match {

      case AccountCreateCommand(_, accountDetails, balance)
      => Seq(AccountCreatedEvent(accountDetails, balance))

      case AccountCreditCommand(_, amount, moneyTransferId) =>
        if (amount.compareTo(account.balance) > 0)
          Seq(AccountCreditFailedInsufficientFundsEvent(moneyTransferId))
        else
          Seq(AccountCreditedEvent(amount, moneyTransferId))

      case AccountDepositCommand(_, amount, moneyTransferId) =>
        Seq(AccountDepositedEvent(amount, moneyTransferId))

      case _ => unknownCommand(command)
    }

  override def applyEvent(account: Account, event: Event): Account =
      event match {
        case AccountCreatedEvent(accountDetails, balance) => Account(accountDetails, balance)
        case AccountCreditedEvent(amount, _) => account.copy(balance = account.balance - amount)
        case AccountDepositedEvent(amount, _) => account.copy(balance = account.balance + amount)
        case _ => account
      }



  override def newInstance(): Account = new Account(null, 0)
}
