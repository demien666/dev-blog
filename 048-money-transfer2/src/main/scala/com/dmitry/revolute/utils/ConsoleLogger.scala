package com.dmitry.revolute.utils

class ConsoleLogger {

  def log(message: String) = println(java.time.LocalDateTime.now().toString + " " + message)

  def info(message: String) = log(s"INFO $message")
  def debug(message: String) = log(s"DEBUG $message")
  def trace(message: String) = log(s"TRACE $message")
  def error(message: String) = log(s"ERROR $message")



}
