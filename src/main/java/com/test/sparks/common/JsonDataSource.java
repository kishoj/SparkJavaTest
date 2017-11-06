package com.test.sparks.common;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface JsonDataSource extends ResourceProperties {

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		return sparkSession.read().json(CLASS_LOADER.getResource(JSON_LOCATION).toString());
	}

}