package com.test.sparks.common.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public interface CouchDBDataSource {
	
	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		return sparkSession.read()
					.format("org.apache.bahir.cloudant")
					.load(sparkSession.conf().get("couchdb.database"));
	}
}
