package com.test.sparks.common.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public enum SparkDataSource implements JsonDataSource, PostgreSQLDataSource, CSVDataSource, 
		CassandraDataSource, MySQLDataSource, MongoDBDataSource, Neo4jDataSource, CouchDBDataSource {
	
	POSTGRESQL(SourceType.POSTGRESQL), 
	MYSQL(SourceType.MYSQL),
	CASSANDRA(SourceType.CASSANDRA), 
	MONGODB(SourceType.MONGODB),
	NEO4J(SourceType.NEO4J),
	JSON(SourceType.JSON), 
	CSV(SourceType.CSV), 
	COUCHDB(SourceType.COUCHDB);
	
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
			case MYSQL:
				return MySQLDataSource.super.getDataSet(sparkSession);
			case JSON:
				return JsonDataSource.super.getDataSet(sparkSession);
			case CSV:
				return CSVDataSource.super.getDataSet(sparkSession);
			case CASSANDRA:
				return CassandraDataSource.super.getDataSet(sparkSession);
			case MONGODB:
				return MongoDBDataSource.super.getDataSet(sparkSession);
			case NEO4J:
				return Neo4jDataSource.super.getDataSet(sparkSession);
			case COUCHDB:
				return CouchDBDataSource.super.getDataSet(sparkSession);
			default:
				return null;
		}
	}

}