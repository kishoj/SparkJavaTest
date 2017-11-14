package com.test.sparks.SparkJavaTest;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.bson.Document;

import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;

public class ConsumingMongoDBSource {

	public static void main(String[] args) {
		SparkSession spark = SparkSession.builder()
				.master("local")
				.appName("MongoSparkConnectorIntro")
				.config("spark.mongodb.input.uri", "mongodb://127.0.0.1/mongodbtest.professions")
				.config("spark.mongodb.output.uri", "mongodb://127.0.0.1/mongodbtest.professions").getOrCreate();

		// Create a JavaSparkContext using the SparkSession's SparkContext
		// object
		JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext());

		/* Start Example: Read data from MongoDB ************************/
		JavaMongoRDD<Document> rdd = MongoSpark.load(jsc);
		/* End Example **************************************************/

		// Analyze data from MongoDB
		System.out.println(rdd.count());
		System.out.println(rdd.first().toJson());
		
		MongoSpark.load(new JavaSparkContext(spark.sparkContext())).toDF().show();

		jsc.close();

	}

}
