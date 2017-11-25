package com.test.sparks.common.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.test.sparks.common.SparkKeyword;

public interface CouchDBDataSource extends SparkKeyword {
	
	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		return sparkSession.read()
					.format(COUCHDB_DB_SOURCE)
					.load(sparkSession.conf().get(COUCHDB_DB_NAME));
	}
}
