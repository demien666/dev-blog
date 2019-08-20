package com.demien.mtransfer.domain

case class Account(accNum: String, balance: BigDecimal) {

  def credit(amount: BigDecimal): Account =
    if (amount.compare(balance) < 0) this.copy(balance = this.balance - amount)
    else throw InsufficientBalanceException()

  def debit(amount: BigDecimal): Account = this.copy(balance = this.balance + amount)

  case class InsufficientBalanceException() extends Exception

}
