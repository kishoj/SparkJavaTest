package com.test.sparks.common;

public interface SparkKeyword {
	
	static final String SPARK_MASTER= "spark://kishoj-Inspiron-5448:7077";
	static final String SPARK_LOCAL= "local";
	static final String SPARK_CORE_MAX = "spark.cores.max";
	static final String SPARK_MULTIPLE_CONTEXT = "spark.driver.allowMultipleContexts";
	static final String MASTER = "spark.master";
	
	static final String JDBC = "jdbc";
	static final String URL = "url";
	static final String DBTABLE = "dbtable";
	static final String USER = "user";
	static final String PASSWORD = "password";
	
	static final String MASTER_IP = "master_ip";	
	static final String CASSANDRA_HOST = "spark.cassandra.connection.host";
	static final String CASSANDRA_LOCAL_DC = "spark.cassandra.connection.local_dc";
	static final String CASSANDRA_SOURCE = "org.apache.spark.sql.cassandra";
	static final String CASSANDRA_PORT = "spark.cassandra.connection.port";
	
	static final String CASSANDRA_DB_TABLE = "table";
	static final String CASSANDRA_DB_KEYSPACE = "keyspace";
	
	static final String SPARK_MONGO_INPUT_URI = "spark.mongodb.input.uri";
	static final String SPARK_MONGO_OUTPUT_URI = "spark.mongodb.output.uri";

}