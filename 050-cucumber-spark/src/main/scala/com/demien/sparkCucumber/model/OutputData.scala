package com.demien.sparkCucumber.model

case object OutputData {
  val STATUS_OK = "OK"
  val STATUS_ERROR = "ERROR"

  val SOURCE_CATEGORY_DEFAULT = "DEFAULT"
  val SOURCE_CATEGORY_SPECIAL = "SPECIAL"

  val UNKNOWN = "UNKNOWN"

  def getOpt[T](inputDataRow: InputData, get: InputData => T): Option[T] = Option(inputDataRow).map(get)

  def fromInputData(inputDataRow: InputData, processingDate: String): OutputData = {
    val optId = getOpt[String](inputDataRow, _.id)
    val optName = getOpt[String](inputDataRow, _.name)

    OutputData(
      header = Header(
        processingStatus = if (optId.isEmpty) STATUS_ERROR else STATUS_OK,
        processingDate = processingDate
      ),
      body = Body(
        sourceDataId = optId.getOrElse(UNKNOWN),
        sourceDataValue = optName.map(_.toUpperCase).getOrElse(UNKNOWN),
        sourceDataCategory = if (optName == UNKNOWN) SOURCE_CATEGORY_SPECIAL else SOURCE_CATEGORY_DEFAULT
      )
    )
  }


}

case class OutputData(header: Header, body: Body)

case class Header(processingStatus: String, processingDate: String)

case class Body(sourceDataId: String, sourceDataValue: String, sourceDataCategory: String)


