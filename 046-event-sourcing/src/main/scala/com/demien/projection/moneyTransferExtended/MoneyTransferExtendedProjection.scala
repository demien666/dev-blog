package com.demien.projection.moneyTransferExtended

import com.demien.domain.moneyTransfer.TransferState.TransferState
import com.demien.domain.moneyTransfer.{MoneyTransfer, TransferState}

class MoneyTransferExtendedProjection(
                                       val accountIdFrom: Int,
                                       val accountNumberFrom: String,
                                       val accountIdTo: Int,
                                       val accountNumberTo: String,
                                       val amount: BigDecimal,
                                       val state: TransferState = TransferState.CREATED) {

  def this(moneyTransfer: MoneyTransfer, accountNumberFrom: String, accountNumberTo: String) = this(
    moneyTransfer.moneyTransferDetails.accountIdFrom,
    accountNumberFrom,
    moneyTransfer.moneyTransferDetails.accountIdTo,
    accountNumberTo,
    moneyTransfer.moneyTransferDetails.amount,
    moneyTransfer.state
  )

}
