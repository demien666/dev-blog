package com.demien.ddd

import com.demien.cqrs.Command

trait Aggregate[T,E] {

  def applyEvent(entity: T, event: E): T = ???

  def processCommand(entity: T, command: Command): Seq[E] = ???

  def newInstance():T = ???

  def unknownCommand(command: Command) = throw new RuntimeException("Command not recognized: " + command)

}
