package com.dmitry.revolute.service

import java.util.concurrent.TimeoutException

import com.dmitry.revolute.domain.Account
import com.dmitry.revolute.repository.AbstractRepository
import org.scalatest.FunSuite

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global


class AccountServiceTest extends FunSuite {

  val accountRepository = new AbstractRepository[Account] {}

  val accountService = new AccountService(accountRepository)

  val slowParams = ServiceParams.default.copy(isSlowMode = true)
  val accountSlowService = new AccountService(accountRepository, slowParams)

  val deadlockParams = slowParams.copy(isDeadlockProtected = false)
  val accountDeadlockService = new AccountService(accountRepository, deadlockParams)


  def createAccounts(id1: String, balance1: BigDecimal, id2: String, balance2: BigDecimal): (Account, Account) = {
    val account1 = Account(id1, balance1)
    val account2 = Account(id2, balance2)

    accountRepository.put(account1)
    accountRepository.put(account2)

    (account1, account2)
  }

  test("Regular test") {
    val (account1, account2)  = createAccounts("1", 10, "2", 20)

    accountService.transfer(account1, account2, 1)
    assert(accountRepository.get(account1.id).balance === 9)
    assert(accountRepository.get(account2.id).balance === 21)

    assertThrows[IllegalArgumentException] {
      accountService.transfer(account1, account2, 100)
    }
    assert(accountRepository.get(account1.id).balance === 9)
    assert(accountRepository.get(account2.id).balance === 21)
  }

  test("Deadlock avoid test") {
     val (account3, account4) = createAccounts("3", 30, "4", 40)
     val futures = Seq(
      Future {
        accountSlowService.transfer(account3, account4, 2)
      },
      Future {
        accountSlowService.transfer(account4, account3, 3)
      }
    )
    Await.result(Future.sequence(futures), Duration.Inf)
    assert(accountRepository.get(account3.id).balance === 31)
    assert(accountRepository.get(account4.id).balance === 39)
  }

  test("Deadlock happen test") {
    val (account5, account6) = createAccounts("5", 50, "6", 60)
    val futures = Seq(
      Future {
        accountDeadlockService.transfer(account5, account6, 2)
      },
      Future {
        accountDeadlockService.transfer(account6, account5, 3)
      }
    )
    assertThrows[TimeoutException] {
      Await.result(Future.sequence(futures), Duration("10s"))
    }
  }

}
