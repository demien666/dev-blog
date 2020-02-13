package com.demien.mtransfer.rest

import com.demien.mtransfer.domain.Account
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AccountControllerTest extends AbstractControllerTest[Account](AccountController.PATH) {

  test("save and get account test") {
    val account = new Account("111", 222)
    val accountId = save(account)
    val saved = getById(accountId, account.getClass)
    assert(saved.accNum === "111")
    assert(saved.balance === 222)
  }

}
