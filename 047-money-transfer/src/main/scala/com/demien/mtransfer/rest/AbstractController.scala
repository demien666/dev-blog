package com.demien.mtransfer.rest

import com.demien.mtransfer.service.Service
import org.apache.log4j.LogManager
import spark.{Request, Response, ResponseTransformer, Spark}


abstract class AbstractController[T](val basePath: String, val service: Service[T], val cl: Class[_]) {

  val logger = LogManager.getLogger("AbstractController")

  def logRequestInfo(request: Request): Unit = {
    logger.info("Processing request:", request.requestMethod(), request.pathInfo())
  }

  def getById(request: Request, response: Response): AnyRef = {
    logRequestInfo(request)
    val id = request.params(":id")
    val result = service.getById(Integer.parseInt(id)).asInstanceOf[AnyRef]
    result
  }

  def save(request: Request, response: Response): AnyRef = {
    logRequestInfo(request)
    service.save(JsonUtil.fromJson(request.body(), cl)).asInstanceOf[AnyRef]
  }

  def sayHello(request: Request, response: Response): AnyRef = "Hello World!"


  val jsonTransformer: ResponseTransformer = JsonUtil.toJson(_)
  val jsonAcceptType = "application/json"

  Spark.get(basePath + "/hello", sayHello(_, _), jsonTransformer)
  Spark.get(basePath + "/:id", getById(_, _), jsonTransformer)
  Spark.post(basePath, jsonAcceptType, save(_, _), jsonTransformer)

  //Spark.get(basePath+"/:id", accountService.getById(_), )


}
