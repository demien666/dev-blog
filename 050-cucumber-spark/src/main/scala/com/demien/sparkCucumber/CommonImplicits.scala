package com.demien.sparkCucumber

import com.demien.sparkCucumber.model.{InputData, OutputData}
import org.apache.spark.sql.Encoders

object CommonImplicits {
  implicit val inputDataEncoder = Encoders.product[InputData]
  implicit val outputDataEncoder = Encoders.product[OutputData]

}
