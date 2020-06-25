package com.dmitry.revolute.domain

case class Operation[T](entity: T, mutator: T => T, description: String = "")
