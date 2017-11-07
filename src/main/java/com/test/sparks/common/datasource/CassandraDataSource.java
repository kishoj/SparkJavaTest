package com.test.sparks.common.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.spark_project.guava.collect.ImmutableMap;

public interface CassandraDataSource {

	default Dataset<Row> getDataSet(SparkSession sparkSession) {
		return sparkSession.read()
				.format("org.apache.spark.sql.cassandra")
				.options(ImmutableMap.of("table", "users", "keyspace", "cassandratest"))
				.load();
	}
}
