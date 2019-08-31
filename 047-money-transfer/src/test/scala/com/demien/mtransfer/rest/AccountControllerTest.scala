package com.demien.mtransfer.rest

import com.demien.mtransfer.domain.Account

class AccountControllerTest extends ControllerTest[Account](AccountController.PATH) {

  test("save account test") {
    val account = new Account("111", 222)
    val accountId = save(account)
    val saved = getById(accountId, account.getClass)
    assert(saved.accNum === "111")
    assert(saved.balance === 222)

  }

}
