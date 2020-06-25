package com.dmitry.revolute.service

object ServiceParams {
  val default: ServiceParams = ServiceParams()
}

case class ServiceParams(isSlowMode: Boolean = false, isDeadlockProtected: Boolean = true)
