package com.demien.domain.account

import com.demien.cqrs.Command
import com.demien.ddd.{Aggregate, Event}
import com.demien.domain.account.AccountCommands.{AccountCommand, CreateAccountCommand, CreditAccountCommand, DepositAccountCommand}
import com.demien.domain.account.AccountEvents.{AccountCreated, AccountCreditFailedInsufficientFunds, AccountCreditPerformed, AccountDepositPerformed, AccountEvent}

case class AccountDetails(val accountNumber: String, val accountCurrency: String)
case class Account(val accountDetails: AccountDetails, val balance: BigDecimal)

object AccountAggregate extends Aggregate[Account,Event] {

   override def processCommand(account: Account, command: Command): Seq[AccountEvent] =
    command match {

      case CreateAccountCommand(accountDetails, balance)
           => Seq(AccountCreated(accountDetails, balance))

      case CreditAccountCommand(amount, moneyTransferId) =>
        if (amount.compareTo(account.balance) > 0)
          Seq(AccountCreditFailedInsufficientFunds(moneyTransferId))
        else
          Seq(AccountCreditPerformed(amount, moneyTransferId))

      case DepositAccountCommand(amount, moneyTransferId) =>
        Seq(AccountDepositPerformed(amount, moneyTransferId))

      case _ => unknownCommand(command)
    }

  override def applyEvent(account: Account, event: Event): Account =
      event match {
        case AccountCreated(accountDetails, balance) => Account(accountDetails, balance)
        case AccountCreditPerformed(amount, _) => account.copy(balance = account.balance - amount)
        case AccountDepositPerformed(amount, _) => account.copy(balance = account.balance + amount)
        case _ => account
      }



  override def newInstance(): Account = new Account(null, 0)
}
