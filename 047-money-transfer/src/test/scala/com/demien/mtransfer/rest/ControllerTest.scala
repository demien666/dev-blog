package com.demien.mtransfer.rest

import com.demien.mtransfer.App
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import spark.Spark

abstract class ControllerTest[T](val servicePath: String) extends FunSuite with BeforeAndAfterAll {

  val basePath = "http://localhost:8080/api/"

  override def beforeAll: Unit = {
    App.main(null)
    Thread.sleep(5000)
  }

  override def afterAll(): Unit = {
    Spark.stop()
  }

  def getById(id: Int, cl: Class[_]): T = {
    val response = RestTestUtil.sendRequest("GET", basePath + servicePath + "/" + id)
    assert(response.status === 200)
    val result = JsonUtil.fromJson(response.body, cl).asInstanceOf[T]
    result
  }

  def save(entity: T): Int = {
    val response = RestTestUtil.sendRequest("POST", basePath + servicePath, JsonUtil.toJson(entity))
    assert(response.status === 200)
    val result = Integer.parseInt(response.body)
    result

  }


}
