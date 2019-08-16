package com.demien.domain.account

import com.demien.cqrs.{Query, QueryHandler}
import com.demien.ddd.Event
import com.demien.domain.account.AccountQueries.{FindAccountsByBalanceRange, FindAccountsByNumberMask}
import com.demien.projection.EventDrivenRepository

class AccountQueryHandler(val repo: EventDrivenRepository[Account, Event]) extends QueryHandler[Account] {

  override def handleQuery(query: Query): Seq[Account] =
    query match {
      case FindAccountsByBalanceRange(balanceFrom, balanceTo)
      => repo.find(account => account.balance.compare(balanceFrom) > 0 && account.balance.compare(balanceTo) < 0)
      case FindAccountsByNumberMask(numberMask)
      => repo.find(account => account.accountDetails.accountNumber.contains(numberMask))
    }


}
