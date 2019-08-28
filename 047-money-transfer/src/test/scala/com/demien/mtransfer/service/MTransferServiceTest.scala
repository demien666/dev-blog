package com.demien.mtransfer.service

import com.demien.mtransfer.domain.{Account, MTransfer}
import com.demien.mtransfer.repo.{InMemoryRepository, OperationExecutionException}
import org.scalatest.FunSuite

class MTransferServiceTest extends FunSuite {

  val accountRepo = new InMemoryRepository[Account]
  val mTransferRepo = new InMemoryRepository[MTransfer]
  val mTransferService = new MTransferService(mTransferRepo, accountRepo)

  val account1 = new Account("100", 100)
  val account2 = new Account("200", 200)

  test("testExecute - success") {

    val accountId1 = accountRepo.save(new Account("100", 100))
    val accountId2 = accountRepo.save(new Account("200", 200))

    val mTransferId = mTransferService.execute(MTransfer(accountId1, accountId2, 20))
    val savedTransfer = mTransferService.getById(mTransferId)
    assert(savedTransfer.state === "COMPLETED")

    val updatedAccount1 = accountRepo.getById(accountId1)
    val updatedAccount2 = accountRepo.getById(accountId2)
    assert(updatedAccount1.balance === 80)
    assert(updatedAccount2.balance === 220)
  }

  test("testExecute - failed") {

    val accountId1 = accountRepo.save(new Account("100", 100))
    val accountId2 = accountRepo.save(new Account("200", 200))

    intercept[OperationExecutionException] {
      val mTransferId = mTransferService.execute(MTransfer(accountId1, accountId2, 250))
      val savedTransfer = mTransferService.getById(mTransferId)
      assert(savedTransfer.state === "FAILED")
    }

    val updatedAccount1 = accountRepo.getById(accountId1)
    val updatedAccount2 = accountRepo.getById(accountId2)
    assert(updatedAccount1.balance === 100)
    assert(updatedAccount2.balance === 200)
  }

}
