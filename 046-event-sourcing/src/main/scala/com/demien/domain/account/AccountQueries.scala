package com.demien.domain.account

import com.demien.cqrs.Query

object AccountQueries {

  case class FindAccountsByBalanceRange(balanceFrom: BigDecimal, balanceTo: BigDecimal) extends Query

  case class FindAccountsByNumberMask(numberMask: String) extends Query

}
