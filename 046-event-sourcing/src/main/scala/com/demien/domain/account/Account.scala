package com.demien.domain.account

import com.demien.cqrs.Command
import com.demien.ddd.{Aggregate, Event}
import com.demien.domain.account.AccountCommands.{AccountCreateCommand, AccountCreditCommand, AccountDepositCommand}
import com.demien.domain.account.AccountEvents._

case class AccountDetails(val accountNumber: String, val accountCurrency: String)
case class Account(val accountDetails: AccountDetails, val balance: BigDecimal)

object AccountAggregate extends Aggregate[Account,Event] {

  override def processCommand(account: Account, command: Command): Seq[Event] =
    command match {

      case AccountCreateCommand(accountId, accountDetails, balance)
      => Seq(AccountCreatedEvent(accountId, accountDetails, balance))

      case AccountCreditCommand(accountId, amount, moneyTransferId) =>
        if (amount.compareTo(account.balance) > 0)
          Seq(AccountCreditFailedInsufficientFundsEvent(accountId, moneyTransferId))
        else
          Seq(AccountCreditedEvent(accountId, amount, moneyTransferId))

      case AccountDepositCommand(accountId, amount, moneyTransferId) =>
        Seq(AccountDepositedEvent(accountId, amount, moneyTransferId))

      case _ => unknownCommand(command)
    }

  override def applyEvent(account: Account, event: Event): Account =
      event match {
        case AccountCreatedEvent(_, accountDetails, balance) => Account(accountDetails, balance)
        case AccountCreditedEvent(_, amount, _) => account.copy(balance = account.balance - amount)
        case AccountDepositedEvent(_, amount, _) => account.copy(balance = account.balance + amount)
        case _ => account
      }



  override def newInstance(): Account = new Account(null, 0)
}
