package com.test.sparks.common.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface MongoDBDataSource {

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		return null;
	}
}
