package com.demien.mtransfer.rest

import com.demien.mtransfer.domain.Account
import com.demien.mtransfer.service.AccountService

object AccountController {
  val PATH = "/api/account"
}

class AccountController(val accountService: AccountService)
  extends AbstractController[Account](AccountController.PATH, accountService, classOf[Account]) {

}
