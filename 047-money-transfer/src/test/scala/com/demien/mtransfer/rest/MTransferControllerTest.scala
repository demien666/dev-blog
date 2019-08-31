package com.demien.mtransfer.rest

import com.demien.mtransfer.App
import com.demien.mtransfer.domain.{Account, MTransfer}

class MTransferControllerTest extends ControllerTest[MTransfer](MTransferController.PATH) {

  test("money transfer test - success") {

    val account1 = new Account("100", 100)
    val account2 = new Account("200", 200)

    val accountId1 = App.accountRepo.save(account1)
    val accountId2 = App.accountRepo.save(account2)

    val mTransferId = save(new MTransfer(accountId1, accountId2, 20))
    sleep(100)

    val saved = App.mTransferRepo.getById(mTransferId)
    assert(saved.state === "COMPLETED")

    val account1Updated = App.accountRepo.getById(accountId1)
    val account2Updated = App.accountRepo.getById(accountId2)
    assert(account1Updated.balance === 80)
    assert(account2Updated.balance === 220)

  }

}
