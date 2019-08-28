package com.demien.mtransfer.rest

import com.demien.mtransfer.domain.Account
import com.demien.mtransfer.service.AccountService

class AccountController(val accountService: AccountService)
  extends Controller[Account]("/api/account", accountService, new Account(null, null).getClass) {

}
