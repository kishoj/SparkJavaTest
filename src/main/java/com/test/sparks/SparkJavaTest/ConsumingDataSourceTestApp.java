package com.test.sparks.SparkJavaTest;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.test.sparks.common.SparkDataSource;
import com.test.sparks.common.SparkKeyword;

public class ConsumingDataSourceTestApp {
	
	public static void main(String[] args) {
		
		SparkSession sparkSession = SparkSession
				  .builder()
				  .appName("SPARK SQL Test")
				  .config(SparkKeyword.MASTER, "local")
				  .getOrCreate();
		
		Dataset<Row> jsonDF =  SparkDataSource.JSON.getDataSet(sparkSession);		
		jsonDF.show();
		jsonDF.createOrReplaceTempView("records");		
		sparkSession.sql("SELECT * FROM records").show();
		
		SparkDataSource.POSTGRESQL.getDataSet(sparkSession).createOrReplaceTempView("users");		
		sparkSession.sql("SELECT * FROM users").show();
		
		// Joining tables in Spark
		sparkSession.sql("SELECT u.id, u.first_name, u.last_name, r.age, r.city FROM users u JOIN records r ON r.firstname = u.first_name").show();
	}
}
