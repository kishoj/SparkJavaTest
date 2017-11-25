package com.test.sparks.common;

public enum SparkAppName {
	
	CASSANDRA_CONSUMER ("Spark Cassandra Consumer"),
	COUCHDB_CONSUMER ("Spark CouchDb Consumer"),
	MONGODB_CONSUMER ("Spark MongoDB Consumer"),
	NEO4J_CONSUMER ("Spark Neo4j Consumer"),
	KAFKA_CONSUMER ("Spark Kafka Consumer"),
	POSTGRESQL_CONSUMER ("Spark Kafka Consumer"),
	MYSQL_CONSUMER ("Spark MySQL Consumer"),
	JSON_CONSUMER ("Spark Json Consumer"),
	CSV_CONSUMER ("Spark CSV Consumer"),
	MULTIPLE_SOURCR_CONSUMER ("Spark Multiple Source Consumer");
	
	private String value;
	
	private SparkAppName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
