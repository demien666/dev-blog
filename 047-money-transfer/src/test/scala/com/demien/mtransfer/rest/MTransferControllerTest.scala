package com.demien.mtransfer.rest

import com.demien.mtransfer.domain.{Account, MTransfer}
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MTransferControllerTest extends AbstractControllerTest[MTransfer](MTransferController.PATH) {

  val accountController = new AbstractControllerTest[Account](AccountController.PATH) {}

  test("money transfer test: success") {

    val account1 = new Account("100", 100)
    val account2 = new Account("200", 200)

    val accountId1 = accountController.save(account1)
    val accountId2 = accountController.save(account2)

    val mTransfer = MTransfer(accountId1, accountId2, 20)

    val mTransferId = save(mTransfer)

    val saved = getById(mTransferId, mTransfer.getClass)
    assert(saved.state === MTransfer.COMPLETED)

    val account1Updated = accountController.getById(accountId1, account1.getClass)
    val account2Updated = accountController.getById(accountId2, account2.getClass)
    assert(account1Updated.balance === 80)
    assert(account2Updated.balance === 220)

  }


  test("money transfer test: fail") {

    val account1 = new Account("100", 100)
    val account2 = new Account("200", 200)

    val accountId1 = accountController.save(account1)
    val accountId2 = accountController.save(account2)

    val mTransfer = MTransfer(accountId1, accountId2, 500)
    val mTransferId = save(mTransfer)
    //sleep(100)

    val saved = getById(mTransferId, mTransfer.getClass)
    assert(saved.state === MTransfer.FAILED)

    val account1Updated = accountController.getById(accountId1, account1.getClass)
    val account2Updated = accountController.getById(accountId2, account2.getClass)
    assert(account1Updated.balance === 100)
    assert(account2Updated.balance === 200)

  }

}
