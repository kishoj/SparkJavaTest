package com.test.sparks.common.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.test.sparks.common.ResourceProperties;

public interface JsonDataSource extends ResourceProperties {

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		return sparkSession.read()
					.json(CLASS_LOADER.getResource(JSON_LOCATION).toString());
	}

}