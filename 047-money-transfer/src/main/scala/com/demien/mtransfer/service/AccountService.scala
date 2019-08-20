package com.demien.mtransfer.service

import com.demien.mtransfer.domain.Account
import com.demien.mtransfer.repo.Repository

class AccountService(accountRepo: Repository[Account]) extends Service[Account](accountRepo)
