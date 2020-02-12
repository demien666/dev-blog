package com.demien.mtransfer.service

import com.demien.mtransfer.domain.Account.InsufficientBalanceException
import com.demien.mtransfer.domain.{Account, MTransfer}
import com.demien.mtransfer.repo.Repository

class MTransferService(mTransferRepo: Repository[MTransfer], accountRepo: Repository[Account])
  extends Service[MTransfer](mTransferRepo) {

  override def save(mTransfer: MTransfer): Int = {
    val id = mTransferRepo.save(mTransfer)
    try {
      accountRepo.biUpdate(
        mTransfer.accIdFrom, acc => acc.credit(mTransfer.amount),
        mTransfer.accIdTo, acc => acc.debit(mTransfer.amount)
      )
      mTransferRepo.update(id, mTransfer => mTransfer.copy(state = MTransfer.COMPLETED))
    } catch {
      case ex: InsufficientBalanceException => {
        mTransferRepo.update(id, mTransfer => mTransfer.copy(state = MTransfer.FAILED))
        ex.printStackTrace()
      }
    }
    id
  }

}
