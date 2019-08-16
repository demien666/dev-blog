package com.demien.cqrs

trait QueryHandler[T] {

  def handleQuery(query: Query): Seq[T]

}
