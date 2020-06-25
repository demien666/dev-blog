package com.dmitry.revolute.domain

import org.scalatest.FunSuite

class AccountTest extends FunSuite {

  test("Account test") {
    println("Hello world!")
    assertThrows[IllegalArgumentException] {
      Account("1", BigDecimal(-1))
    }

    assertThrows[IllegalArgumentException] {
      Account("1", BigDecimal(100500))
    }

    val acc = Account("1", BigDecimal(10))
    assertThrows[IllegalArgumentException] {
      acc.dec(BigDecimal(20))
    }
    assertThrows[IllegalArgumentException] {
      acc.dec(BigDecimal(100500))
    }

  }



}
