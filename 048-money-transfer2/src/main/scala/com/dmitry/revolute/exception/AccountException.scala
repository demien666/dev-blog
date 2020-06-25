package com.dmitry.revolute.exception

import com.dmitry.revolute.domain.Account

class AccountException(account: Account, message: String) extends RuntimeException (message)
