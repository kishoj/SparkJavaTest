package com.test.sparks.common.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public enum SparkDataSource implements JsonDataSource, PostgreSQLDataSource {
	
	POSTGRESQL(SourceType.POSTGRESQL), 
	CASSANDRA(SourceType.CASSANDRA), 
	JSON(SourceType.JSON), 
	CSV(SourceType.CSV);
	
	private SourceType source;

	private SparkDataSource(SourceType source) {
		this.source = source;
	}
	
	public SourceType getSource() {
		return source;
	}

	@Override
	public Dataset<Row> getDataSet(SparkSession sparkSession) {
		switch(this) {
			case POSTGRESQL:
				return PostgreSQLDataSource.super.getDataSet(sparkSession);
			case JSON:
				return JsonDataSource.super.getDataSet(sparkSession);
			default:
				return null;
		}
	}

}