package com.demien.domain.moneyTransfer

import com.demien.cqrs.Query

object MoneyTransferQueries {

  case class FindMoneyTransferByAccount(accountId: Int) extends Query

}
