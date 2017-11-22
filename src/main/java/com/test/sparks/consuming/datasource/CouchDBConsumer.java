package com.test.sparks.consuming.datasource;

import org.apache.spark.sql.SparkSession;

public class CouchDBConsumer {
	
	public static void main(String[] args) {
		SparkSession spark = SparkSession
				.builder()
				.appName("JavaExample")
				.master("local[*]")
				.config("cloudant.protocol", "http")
				.config("cloudant.host","127.0.0.1:5984")
			    .config("cloudant.username", "admin")
			    .config("cloudant.password","pass")
				.getOrCreate();
		
		spark.read().format("org.apache.bahir.cloudant").load("favorites").show();
	}
}
