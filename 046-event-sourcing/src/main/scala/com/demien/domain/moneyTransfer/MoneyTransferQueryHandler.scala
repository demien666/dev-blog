package com.demien.domain.moneyTransfer

import com.demien.cqrs.{Query, QueryHandler}
import com.demien.ddd.Event
import com.demien.domain.moneyTransfer.MoneyTransferQueries.FindMoneyTransferByAccount
import com.demien.projection.EventDrivenRepository

class MoneyTransferQueryHandler(val repo: EventDrivenRepository[MoneyTransfer, Event]) extends QueryHandler[MoneyTransfer] {
  override def handleQuery(query: Query): Seq[MoneyTransfer] =
    query match {
      case FindMoneyTransferByAccount(accountId) =>
        repo.find(transfer => transfer.moneyTransferDetails.accountIdFrom == accountId || transfer.moneyTransferDetails.accountIdTo == accountId)
    }
}
