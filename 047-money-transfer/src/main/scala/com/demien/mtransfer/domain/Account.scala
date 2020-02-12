package com.demien.mtransfer.domain

import com.demien.mtransfer.domain.Account.InsufficientBalanceException

object Account {

  class InsufficientBalanceException() extends Exception

}

class Account(val accNum: String, val balance: BigDecimal) {

  def credit(amount: BigDecimal): Account =
    if (amount.compare(balance) < 0) new Account(this.accNum, this.balance - amount)
    else throw new InsufficientBalanceException()

  def debit(amount: BigDecimal): Account = new Account(this.accNum, this.balance + amount)


}
