package com.demien.mtransfer

import com.demien.mtransfer.domain.{Account, MTransfer}
import com.demien.mtransfer.repo.InMemoryRepository
import com.demien.mtransfer.rest.{AccountController, MTransferController}
import com.demien.mtransfer.service.{AccountService, MTransferService}
import spark.Spark

object App {

  Spark.port(8080)
  val accountRepo = new InMemoryRepository[Account]
  val accountService = new AccountService(accountRepo)
  val accountController: AccountController = new AccountController(accountService)

  val mTransferRepo = new InMemoryRepository[MTransfer]
  val mTransferService = new MTransferService(mTransferRepo, accountRepo)
  val mTransferController = new MTransferController(mTransferService)


  def main(args: Array[String]): Unit = {

  }

}
