package com.demien.sparkCucumber.processing

import org.apache.spark.sql.DataFrame

object DataFrameMapper {
  def getByName(mapperName: String): DataFrameMapper = mapperName match {
    case "InputToOutputMapper" => new InputToOutputMapper

    case _ => throw new Exception(s"Unknown mapper: $mapperName ")
  }
}

trait DataFrameMapper{

  def mapDf(input: DataFrame, processingDate: String): DataFrame



}
