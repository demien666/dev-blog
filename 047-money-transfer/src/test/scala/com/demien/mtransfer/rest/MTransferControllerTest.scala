package com.demien.mtransfer.rest

import com.demien.mtransfer.domain.{Account, MTransfer}
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MTransferControllerTest extends AbstractControllerTest {

  val mTransferController = new RestTestController[MTransfer](MTransferController.PATH)
  val accountController = new RestTestController[Account](AccountController.PATH)

  test("money transfer test: success") {

    val account1 = new Account("100", 100)
    val account2 = new Account("200", 200)

    val accountId1 = accountController.performPOST(account1)
    val accountId2 = accountController.performPOST(account2)

    val mTransfer = MTransfer(accountId1, accountId2, 20)

    val mTransferId = mTransferController.performPOST(mTransfer)

    val saved = mTransferController.performGET(mTransferId, mTransfer.getClass)
    assert(saved.state === MTransfer.COMPLETED)

    val account1Updated = accountController.performGET(accountId1, account1.getClass)
    val account2Updated = accountController.performGET(accountId2, account2.getClass)
    assert(account1Updated.balance === 80)
    assert(account2Updated.balance === 220)

  }


  test("money transfer test: fail") {

    val account1 = new Account("100", 100)
    val account2 = new Account("200", 200)

    val accountId1 = accountController.performPOST(account1)
    val accountId2 = accountController.performPOST(account2)

    val mTransfer = MTransfer(accountId1, accountId2, 500)
    val mTransferId = mTransferController.performPOST(mTransfer)

    val saved = mTransferController.performGET(mTransferId, mTransfer.getClass)
    assert(saved.state === MTransfer.FAILED)

    val account1Updated = accountController.performGET(accountId1, account1.getClass)
    val account2Updated = accountController.performGET(accountId2, account2.getClass)
    assert(account1Updated.balance === 100)
    assert(account2Updated.balance === 200)

  }

}
