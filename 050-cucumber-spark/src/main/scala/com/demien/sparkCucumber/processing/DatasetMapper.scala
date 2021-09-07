package com.demien.sparkCucumber.processing

import org.apache.spark.sql.{DataFrame, Dataset}

trait DatasetMapper[A, B] {

  def mapDs(input: Dataset[A], processingDate: String): Dataset[B]

}
