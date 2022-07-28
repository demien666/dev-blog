from pyspark.sql import SparkSession
from pyspark.sql import types as t
from pyspark.sql import functions as f
from pyspark.sql.functions import col

schema = t.StructType(
    [
        t.StructField('id', t.StringType(), True),
        t.StructField('amount', t.IntegerType(), True),
    ],
)

def main():
    spark = (SparkSession
            .builder
            .appName('quickstart-streaming-kafka')
            .getOrCreate())
    spark.sparkContext.setLogLevel('WARN')

    source = (spark
          .readStream
          .format('kafka')
          .option('kafka.bootstrap.servers', 'localhost:9092')
          .option('subscribe', 'input00')
          .load()
          )

    source.printSchema()

    df = source.selectExpr('CAST(value AS STRING)', 'offset')
    df1 = (
        df.select(f.from_json('value', schema).alias('data'))
        .withColumn('timestamp', f.current_timestamp())
        .withWatermark("timestamp", "1 minutes") 
    )

    df1.printSchema()
    df1.createOrReplaceTempView("events")
    df2 = spark.sql("SELECT data.id, sum(data.amount) as total_amount FROM events GROUP BY data.id")

    console = df2.writeStream.outputMode("complete").format('console')
    console.start().awaitTermination()


if __name__ == "__main__":
    main()