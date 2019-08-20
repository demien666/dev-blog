package com.demien.mtransfer.service

import com.demien.mtransfer.domain.{Account, MTransfer}
import com.demien.mtransfer.repo.{OperationExecutionException, Repository}

class MTransferService(mTransferRepo: Repository[MTransfer], accountRepo: Repository[Account])
  extends Service[MTransfer](mTransferRepo) {

  def execute(mTransfer: MTransfer): Int = {
    val id = mTransferRepo.save(mTransfer)
    try {
      accountRepo.bulkUpdate(Seq(
        (mTransfer.accIdFrom, acc => acc.credit(mTransfer.amount)),
        (mTransfer.accIdTo, acc => acc.debit(mTransfer.amount))
      ))
      mTransferRepo.update(id, mTransfer.copy(state = "COMPLETED"))
    } catch {
      case ex: OperationExecutionException => {
        mTransferRepo.update(id, mTransfer.copy(state = "FAILED"))
        throw ex
      }
    }
    id
  }

}
