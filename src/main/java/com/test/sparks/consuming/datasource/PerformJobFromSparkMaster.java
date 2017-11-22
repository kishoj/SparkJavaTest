package com.test.sparks.consuming.datasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.test.sparks.common.SparkKeyword;
import com.test.sparks.common.datasource.SparkDataSource;

public class PerformJobFromSparkMaster {
	
	public static void main(String[] args) {
		SparkSession sparkSession = SparkSession
				  .builder()
				  .appName("SPARK SQL Test1")
				  .master(SparkKeyword.SPARK_MASTER)
				  //.master(SparkKeyword.SPARK_MASTER)
				  //.config(SparkKeyword.MASTER, SparkKeyword.SPARK_MASTER)
				  .getOrCreate();
		
		/*Dataset<Row> jsonDF =  SparkDataSource.JSON.getDataSet(sparkSession);		
		jsonDF.show();
		jsonDF.createOrReplaceTempView("records");		
		sparkSession.sql("SELECT * FROM records").show();*/
		
		SparkDataSource.POSTGRESQL.getDataSet(sparkSession).createOrReplaceTempView("users");		
		sparkSession.sql("SELECT * FROM users").show();
		
		// Joining tables in Spark
		//sparkSession.sql("SELECT u.id, u.first_name, u.last_name, r.age, r.city FROM users u JOIN records r ON r.firstname = u.first_name").show();

	}
}
