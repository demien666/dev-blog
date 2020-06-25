package com.dmitry.revolute.service

import com.dmitry.revolute.domain.{Account, Operation}
import com.dmitry.revolute.repository.Repository

class AccountService(repository: Repository[Account], params: ServiceParams = ServiceParams.default) extends AbstractService[Account](params) {

  def transfer(accountFrom: Account, accountTo: Account, amount: BigDecimal): Unit = {
    doInTransaction(
      repository,
        Operation(accountFrom, account => account.dec(amount), s"#${accountFrom.id}+$amount"),
        Operation(accountTo, account => account.inc(amount), s"#${accountTo.id}-$amount")
    )
  }

}
