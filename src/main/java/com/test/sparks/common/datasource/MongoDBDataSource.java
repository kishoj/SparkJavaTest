package com.test.sparks.common.datasource;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.mongodb.spark.MongoSpark;

public interface MongoDBDataSource {

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		return MongoSpark
				.load(new JavaSparkContext(sparkSession.sparkContext()))
				.toDF();
	}
}
