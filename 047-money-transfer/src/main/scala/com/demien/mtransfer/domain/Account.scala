package com.demien.mtransfer.domain

class Account(val accNum: String, val balance: BigDecimal) {

  def credit(amount: BigDecimal): Account =
    if (amount.compare(balance) < 0) new Account(this.accNum, this.balance - amount)
    else throw InsufficientBalanceException()

  def debit(amount: BigDecimal): Account = new Account(this.accNum, this.balance + amount)

  case class InsufficientBalanceException() extends Exception

}
