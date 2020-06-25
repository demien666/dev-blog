package com.dmitry.revolute.domain

case class Account(accountId: String, balance: BigDecimal) extends Entity (accountId){
  val SUSPICIOUS_BIG_THRESHOLD = 1000
  require(balance >= 0, "Balance should be positive")
  require(balance <= SUSPICIOUS_BIG_THRESHOLD, "Balance is too big")

  private def balanceOp(sign: Int, amount: BigDecimal): Account = this.copy(balance = this.balance + sign*amount)

  def inc(amount: BigDecimal): Account = balanceOp(1, amount)
  def dec(amount: BigDecimal): Account = balanceOp(-1, amount)

}
