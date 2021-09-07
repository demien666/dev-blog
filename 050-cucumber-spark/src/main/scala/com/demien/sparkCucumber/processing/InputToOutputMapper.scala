package com.demien.sparkCucumber.processing

import com.demien.sparkCucumber.CommonImplicits
import com.demien.sparkCucumber.model.{InputData, OutputData}
import org.apache.spark.sql.{DataFrame, Dataset}

class InputToOutputMapper() extends DatasetMapper[InputData, OutputData] with DataFrameMapper {

  import CommonImplicits._

  override def mapDs(input: Dataset[InputData], processingDate: String): Dataset[OutputData] =
    input.map(inputDataRow => OutputData.fromInputData(inputDataRow, processingDate))

  override def mapDf(input: DataFrame, processingDate: String): DataFrame = mapDs(input.as[InputData], processingDate).toDF()
}
