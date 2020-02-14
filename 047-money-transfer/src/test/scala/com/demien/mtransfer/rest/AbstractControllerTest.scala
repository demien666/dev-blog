package com.demien.mtransfer.rest

import com.demien.mtransfer.App
import org.scalatest.{BeforeAndAfterAll, FunSuite}

class AbstractControllerTest extends FunSuite with BeforeAndAfterAll {

  def sleep(time: Long) = Thread.sleep(time)

  override def beforeAll: Unit = {
    App.main(null)
    sleep(5000)
  }

}
