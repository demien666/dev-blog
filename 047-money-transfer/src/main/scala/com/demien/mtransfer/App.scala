package com.demien.mtransfer

import com.demien.mtransfer.domain.Account
import com.demien.mtransfer.repo.InMemoryRepository
import com.demien.mtransfer.rest.AccountController
import com.demien.mtransfer.service.AccountService
import spark.Spark

object App {

  Spark.port(8080)
  val accountRepo = new InMemoryRepository[Account]
  val accountService = new AccountService(accountRepo)
  val accountController: AccountController = new AccountController(accountService)


  def main(args: Array[String]): Unit = {

  }

}
