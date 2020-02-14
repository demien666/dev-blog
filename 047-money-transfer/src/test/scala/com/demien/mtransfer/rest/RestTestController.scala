package com.demien.mtransfer.rest

import com.demien.mtransfer.App
import org.scalatest.{BeforeAndAfterAll, FunSuite}

object RestTestController {
  App.main(null)
  //sleep(5000)
}

//@RunWith(classOf[JUnitRunner])
class RestTestController[T](val servicePath: String) extends FunSuite with BeforeAndAfterAll {

  val basePath = "http://localhost:8080"

  def sleep(time: Long) = Thread.sleep(time)

  override def beforeAll: Unit = {
    App.main(null)
    sleep(5000)
  }

  def performGET(id: Int, cl: Class[_]): T = {
    val response = RestTestUtil.sendRequest("GET", basePath + servicePath + "/" + id)
    assert(response.status === 200)
    val result = JsonUtil.fromJson(response.body, cl).asInstanceOf[T]
    result
  }

  def performPOST(entity: T): Int = {
    val response = RestTestUtil.sendRequest("POST", basePath + servicePath, JsonUtil.toJson(entity))
    assert(response.status === 200)
    val result = Integer.parseInt(response.body)
    result
  }


}
