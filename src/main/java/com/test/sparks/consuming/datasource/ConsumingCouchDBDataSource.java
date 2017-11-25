package com.test.sparks.consuming.datasource;

import org.apache.spark.sql.SparkSession;

import com.test.sparks.common.SparkAppName;
import com.test.sparks.common.SparkKeyword;

public class ConsumingCouchDBDataSource implements SparkKeyword {
	
	public static void main(String[] args) {
		SparkSession spark = SparkSession
				.builder()
				.appName(SparkAppName.COUCHDB_CONSUMER.getValue())
				.master(SPARK_LOCAL)
				.config(COUCHDB_DB_PROTOCOL, "http")
				.config(COUCHDB_DB_HOST,"127.0.0.1:5984")
			    .config(COUCHDB_DB_USENAME, "admin")
			    .config(COUCHDB_DB_PASSWORD,"pass")
				.getOrCreate();
		
		spark.read().format(COUCHDB_DB_SOURCE).load("favorites").show();
	}
}
