package com.demien.mtransfer.rest

import com.google.gson.{Gson, GsonBuilder}


object JsonUtil {

  val gson: Gson = new GsonBuilder()
    .setLenient()
    .create();

  def toJson(entity: Any): String = gson.toJson(entity)

  def fromJson[T](json: String, cl: Class[_]): T = gson.fromJson(json, cl)


}